package com.coderunning.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource("classpath:dbconfig.properties")
public class ProfileConfig implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    private StringValueResolver stringValueResolver;

    @Bean("devDataSource")
    @Profile("dev")
    public DataSource dataSourceDev() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setUser("root");
//        String pasword = stringValueResolver.resolveStringValue("${db.password}");
        comboPooledDataSource.setPassword("0.0.0123");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mine_test");
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");

        return comboPooledDataSource;
    }

    @Bean("testDataSource")
    public DataSource dataSourceTest() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("0.0.0123");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mine_test");
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");

        return comboPooledDataSource;
    }

    @Bean("prodDataSource")
    @Profile("prod")
    public DataSource dataSourceProd() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("0.0.0123");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mine_test");
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");

        return comboPooledDataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
    }
}
