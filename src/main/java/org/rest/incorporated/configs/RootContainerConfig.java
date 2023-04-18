package org.rest.incorporated.configs;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"org.rest.incorporated.service", "org.rest.incorporated.dao"})
@PropertySource("classpath:database.properties")
//set up a filter to avoid mvc components and configs
public class RootContainerConfig {
    private final String ENV_POSTGRES_DRIVER_CLASS_NAME ="postgres.driver-class";
    private final String ENV_POSTGRES_URL ="postgres.url";
    private final String ENV_POSTGRES_USERNAME ="postgres.username";
    private final String ENV_POSTGRES_PASSWORD ="postgres.password";
    private final Environment env;

    public RootContainerConfig(Environment env) {
        this.env = env;
    }

    @Bean("jdbc_datasource")
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(ENV_POSTGRES_DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(ENV_POSTGRES_URL));
        dataSource.setUsername(env.getProperty(ENV_POSTGRES_USERNAME));
        dataSource.setPassword(env.getProperty(ENV_POSTGRES_PASSWORD));
        return dataSource;
    }
    @Bean("jdbc_template")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
