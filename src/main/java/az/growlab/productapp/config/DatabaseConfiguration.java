package az.growlab.productapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean("DB.PRODUCT")
    @ConfigurationProperties(prefix="spring.datasource.product")
    public DataSource productdb(){
        return DataSourceBuilder.create().build();
    }
}
