package ru.ofd.lk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class Beans {
    @Bean
    @Scope("singleton")
    public JdbcTemplate getTemplate(@Qualifier("dataSource") @Autowired DataSource ds){
        return new JdbcTemplate(ds);
    }
}
