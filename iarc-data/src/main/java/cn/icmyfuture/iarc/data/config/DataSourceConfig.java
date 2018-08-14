package cn.icmyfuture.iarc.data.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
@Configuration
@MapperScan(basePackages = "cn.icmyfuture.iarc.data.dao",
        sqlSessionFactoryRef = "iarc_data_SqlSessionFactory")
public class DataSourceConfig {
    @ConfigurationProperties("spring.datasource.druid.ijava")
    @Bean(name = "iarc_data_DataSource", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "iarc_data_SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("iarc_data_DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:cn/icmyfuture/iarc/data/mapper/*.xml"));
        return sessionFactoryBean.getObject();
    }
}
