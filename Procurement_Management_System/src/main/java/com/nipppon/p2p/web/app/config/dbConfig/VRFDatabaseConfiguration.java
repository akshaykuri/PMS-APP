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
		entityManagerFactoryRef = "vrfEntityManager",
		basePackages = "com.nipppon.p2p.web.app.repository.VRF",
		transactionManagerRef = "vrfTransactionManager"
)
public class VRFDatabaseConfiguration{
	/* *
     * Datasource Configuration
     * */
	@Bean("vrfDb")
	@ConfigurationProperties(prefix = "vrf.datasource")
	public DataSource vrfDataSource(){
		return DataSourceBuilder.create().build();
	}

	/* *
     * Entity Manager Configuration
     * */
	@Bean("vrfEntityManager")
	public LocalContainerEntityManagerFactoryBean vrfEntityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
		Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        return entityManagerFactoryBuilder
        		.dataSource(vrfDataSource())
        		.packages("com.nipppon.p2p.web.app.entity.VRF")
        		.persistenceUnit("vrf_master")
        		.properties(jpaProperties)
        		.build();
	}

    /* *
     * Transaction Manager Configuration
     * */
    @Bean("vrfTransactionManager")
    public PlatformTransactionManager vrfTransactionManager(@Qualifier("vrfEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}