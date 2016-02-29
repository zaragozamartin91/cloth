package cloth.config.data;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@ComponentScan(basePackages = { "cloth.data" })
public class DataConfig {
	@Profile("test")
	@Bean(name = "hibernateProperties")
	public Properties memoryHibernateProperties() throws IOException {
		Properties hibernateProperties = new Properties();

		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
		hibernateProperties.put("hibernate.connection.username", "root");
		hibernateProperties.put("hibernate.connection.password", "root");
		hibernateProperties.put("hibernate.connection.url", "jdbc:hsqldb:mem:mkyong");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "create");

		return hibernateProperties;
	}

	@Profile("dev")
	@Bean(name = "hibernateProperties")
	public Properties mysqlHibernateProperties() throws IOException {
		Properties hibernateProperties = new Properties();

		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		hibernateProperties.put("hibernate.connection.username", "root");
		hibernateProperties.put("hibernate.connection.password", "root");
		hibernateProperties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/mkyong");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "create");

		return hibernateProperties;
	}

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean memorySessionFactory(@Qualifier("hibernateProperties") Properties hibernateProperties) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setHibernateProperties(hibernateProperties);
		sessionFactory.setPackagesToScan("cloth.data");

		return sessionFactory;
	}

}
