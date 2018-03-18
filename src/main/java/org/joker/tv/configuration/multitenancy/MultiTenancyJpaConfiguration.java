package org.joker.tv.configuration.multitenancy;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
public class MultiTenancyJpaConfiguration {

	private static final String ENTITY_PACKAGE = "org.joker.tv.model.entity";

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JpaProperties jpaProperties;

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Autowired
	private MultiTenantConnectionProvider multiTenantConnectionProvider;

	@Autowired
	private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		Map<String, Object> hibernateProps = new LinkedHashMap<>();
		hibernateProps.putAll(jpaProperties.getHibernateProperties(dataSource));
		hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
		hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
		hibernateProps.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(ENTITY_PACKAGE);
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaPropertyMap(hibernateProps);
		return em;
	}
}
