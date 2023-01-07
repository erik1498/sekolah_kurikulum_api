package com.erickhene.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@MapperScan(
    basePackages  = "com.erickhene.dao",
    sqlSessionFactoryRef = "erickheneSqlSessionFactory"
)
public class IbatisDataSource {

    @Bean(name = "erickheneDataSource")
    public DataSource erickheneDataSource(){
        final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(AppProperties.driverClassName);
        driverManagerDataSource.setUrl(AppProperties.dataSourceUrl);
        driverManagerDataSource.setUsername(AppProperties.dataSourceUsername);
        driverManagerDataSource.setPassword(AppProperties.dataSourcePassword);
        return driverManagerDataSource;
    }

    @Bean(name = "erickheneSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("erickheneDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
            "classpath*:mapper/*.xml"
        ));
        return sqlSessionFactoryBean.getObject();
    }

}
