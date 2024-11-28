/*pmsdb DB Configuration*/
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
		entityManagerFactoryRef = "p2pEntityManager",
		basePackages = "com.nipppon.p2p.web.app.repository.P2P",
		transactionManagerRef = "p2pTransactionManager"
)
public class P2PDatabaseConfiguration{
	/* *
     * Datasource Configuration
     * */
	@Bean("p2pDb")
	@ConfigurationProperties(prefix = "p2p.datasource")
	public DataSource p2pDataSource(){
		return DataSourceBuilder.create().build();
	}

	/* *
     * Entity Manager Configuration
     * */
	@Bean("p2pEntityManager")
	public LocalContainerEntityManagerFactoryBean p2pEntityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
		Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        return entityManagerFactoryBuilder
        		.dataSource(p2pDataSource())
        		.packages("com.nipppon.p2p.web.app.entity.P2P")
        		.persistenceUnit("prRequest")
        		.properties(jpaProperties)
        		.build();
	}

    /* *
     * Transaction Manager Configuration
     * */
    @Bean("p2pTransactionManager")
    public PlatformTransactionManager p2pTransactionManager(@Qualifier("p2pEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}