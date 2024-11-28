/*leavemanagement DB Configuration*/
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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = "hrmsEntityManager",
	basePackages = "com.nipppon.p2p.web.app.repository.HRMS",
	transactionManagerRef = "hrmsTransactionManager"
)
public class HRMSDatabaseConfiguration{

	/* *
     * Datasource Configuration
     * */
	@Primary
	@Bean("hrmsDb")
	@ConfigurationProperties(prefix = "hrms.datasource")
	public DataSource hrmsDataSource() {
		return DataSourceBuilder.create().build();
	}

	/* *
     * Entity Manager Configuration
     * */
	@Primary
	@Bean("hrmsEntityManager")
	public LocalContainerEntityManagerFactoryBean hrmsEntityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
		Map<String, Object> jpaProperties = new HashMap<>();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        return entityManagerFactoryBuilder
        		.dataSource(hrmsDataSource())
        		.packages("com.nipppon.p2p.web.app.entity.HRMS")
        		.persistenceUnit("user")
        		.properties(jpaProperties)
        		.build();
	}

	/* *
     * Transaction Manager Configuration
     * */
	@Primary
	@Bean("hrmsTransactionManager")
	public PlatformTransactionManager hrmsTransactionManager(@Qualifier("hrmsEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}
}