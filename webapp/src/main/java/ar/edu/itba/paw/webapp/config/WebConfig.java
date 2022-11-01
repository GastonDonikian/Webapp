package ar.edu.itba.paw.webapp.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@EnableWebMvc
@EnableTransactionManagement
@EnableAsync
@ComponentScan({"ar.edu.itba.paw.webapp.controllers", "ar.edu.itba.paw.services", "ar.edu.itba.paw.persistence"})
@Configuration
public class WebConfig {

    @Value("classpath:schema.sql")
    private Resource schemaSql;

    @Value("classpath:functions.sql")
    private Resource functionsSql;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);


    @Bean
    public InternalResourceViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20848820);
        return multipartResolver;
    }

    @Bean
    public DataSource dataSource() {
        final SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriverClass(org.postgresql.Driver.class);

        LOGGER.debug("Setting up data Source");
//        ds.setUrl("jdbc:postgresql://localhost:5433/paw");
//        ds.setUsername("postgres");
//        ds.setPassword("1234");


        ds.setUrl("jdbc:postgresql://localhost:5432/paw-2021b-8");
        ds.setUsername("paw-2021b-8");
        ds.setPassword("81WLukgmm");
        return ds;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ar.edu.itba.paw.models");
        factoryBean.setDataSource(dataSource());

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);

        final Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");
// Si ponen esto en prod, hay tabla!!!
//        FixMe: SACAR ESTO DE PRODUCCION!!!!!!!!!!!!!!!!!!!!!!
//        TODO: SACAR ESTO DE PRODUCCION!!!!!!!!!!!!!!!!!!!!!!!
//        props.setProperty("hibernate.show_sql", "true");
//        props.setProperty("format_sql", "true");
        factoryBean.setJpaProperties(props);

        return factoryBean;
    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf)
    {
        return new JpaTransactionManager(emf);
    }
}



