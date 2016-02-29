package hibernateexamples.journaldev.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@ComponentScan(basePackages = { "com.journaldev.dao" })
public class Config {

	@Profile("testMemory")
	@Bean(name = "hibernateProperties")
	public Properties memoryHibernateProperties() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource(
				"memoryHibernate.properties");
		Properties hibernateProperties = new Properties();
		hibernateProperties.load(classPathResource.getInputStream());
		return hibernateProperties;
	}
	
	@Profile("testMysql")
	@Bean(name = "hibernateProperties")
	public Properties mysqlHibernateProperties() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource(
				"memoryHibernate.properties");
		Properties hibernateProperties = new Properties();
		hibernateProperties.load(classPathResource.getInputStream());
		return hibernateProperties;
	}

	@Profile("testMemory")
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean memorySessionFactory(
			@Qualifier("hibernateProperties") Properties hibernateProperties) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setHibernateProperties(hibernateProperties);
		sessionFactory.setPackagesToScan("com.journaldev.model");

		return sessionFactory;
	}

	@Profile("testMysql")
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean mysqlSessionFactory(
			@Qualifier("hibernateProperties") Properties hibernateProperties) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setHibernateProperties(hibernateProperties);
		sessionFactory.setPackagesToScan("com.journaldev.model");

		return sessionFactory;
	}
}
