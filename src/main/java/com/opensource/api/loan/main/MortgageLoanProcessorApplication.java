package com.opensource.api.loan.main;

import javax.sql.DataSource;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.opensource.api.loan.config.JerseyConfig;
@Configuration
@ComponentScan("com.opensource.api.loan")

@EnableAutoConfiguration
public class MortgageLoanProcessorApplication extends SpringBootServletInitializer {

    private final String dbUrl = "jdbc:postgresql://creditreportdb.cggvdhxc3vis.us-east-1.rds.amazonaws.com:5432/creditreports";
    private final String dbUser = "mortgageloanapp";
    private final String dbPassword = "mortgageloanapp";
    private final String driverClassName = "org.postgresql.Driver";
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(applicationClass, args);
        for(String beanName : context.getBeanFactory().getBeanDefinitionNames())
        {
            System.out.println(beanName);
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<MortgageLoanProcessorApplication> applicationClass = MortgageLoanProcessorApplication.class;
    
    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/mortgage-loan/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        return registration;
    }
    
    @Bean(name="dataSource")
    public DataSource getDataSource()
    {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        return dataSource;

    }
    
    
}
