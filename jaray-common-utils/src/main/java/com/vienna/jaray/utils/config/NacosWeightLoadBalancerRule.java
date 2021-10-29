package com.vienna.jaray.utils.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 解决修改权重后不实时生效，实现基于Nacos权重的负载均衡
 */

@Slf4j
public class NacosWeightLoadBalancerRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        // 自动读取配置文件，并初始化NacosWeightLoadBalancerRule
    }

    @Override
    public Server choose(Object o) {
        // ribbon入口
        BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
        //获取要请求的微服务名称
        String name = baseLoadBalancer.getName();
        //获取服务发现的相关API
        NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();

        try {
            // nacos client通过基于权重的负载均衡算法，算出一个健康的服务实例以供调用
            Instance instance = namingService.selectOneHealthyInstance(name);
            log.info("nacos选择负载的实例是port={}", instance.getPort());
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.error("获取服务实例异常：{}", e.getMessage());
        }
        return null;
    }
}
