package com.nipppon.p2p.web.app.config.dbConfig;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "crfEntityManager",
		basePackages = "com.nipppon.p2p.web.app.repository.CRF",
		transactionManagerRef = "crfTransactionManager"
)
public class CRFDatabaseConfiguration{
	/* *
     * Datasource Configuration
     * */
	@Bean("crfDb")
	@ConfigurationProperties(prefix = "crf.datasource")
	public DataSource crfDataSource(){
		return DataSourceBuilder.create().build();
	}

	/* *
     * Entity Manager Configuration
     * */
	@Bean("crfEntityManager")
	public LocalContainerEntityManagerFactoryBean crfEntityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
		Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        return entityManagerFactoryBuilder
        		.dataSource(crfDataSource())
        		.packages("com.nipppon.p2p.web.app.entity.CRF")
        		.persistenceUnit("form_customer_details")
        		.properties(jpaProperties)
        		.build();
	}

    /* *
     * Transaction Manager Configuration
     * */
    @Bean("crfTransactionManager")
    public PlatformTransactionManager crfTransactionManager(@Qualifier("crfEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}