package az.growlab.productapp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfiguration {

    @Bean()
    public NamedParameterJdbcTemplate productJdbcTemplate(@Qualifier("DB.PRODUCT") DataSource datasource) {
        return new NamedParameterJdbcTemplate(datasource);
    }
}
