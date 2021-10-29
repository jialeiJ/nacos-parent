package com.vienna.jaray.utils.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局设置基于Nacos权重的负载均衡
 * 全局配置：调用其他微服务，一律使用基于Nacos权重的负载均衡算法
 *
 * 局部配置
 * # 被调用的微服务名
 * jaray-order-producer:
 *   ribbon:
 *     # 指定使用Nacos提供的基于权重的负载均衡
 *     NFLoadBalancerRuleClassName: com.water.step.service.user.nacos.NacosWeightLoadBalancerRule
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new NacosWeightLoadBalancerRule();
    }
}
