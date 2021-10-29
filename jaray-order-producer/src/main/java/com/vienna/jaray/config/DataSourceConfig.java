package com.vienna.jaray.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig extends DataSourceBaseConfig {
    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Primary
    @Bean("dataSource")
    public DataSourceProxy dataSource(DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        String typeAliasesPackage = env.getProperty("mybatis.type-aliases-package");
        String configLocation = env.getProperty("mybatis.config-location");
        String mapperLocations = env.getProperty("mybatis.mapper-locations");
        factoryBean.setDataSource(dataSourceProxy);
        factoryBean.setTypeAliasesPackage(setTypeAliasesPackage(typeAliasesPackage));
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));

        return factoryBean.getObject();
    }
}
