package pl.zaprogramuj.webapplication.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateJPAConfiguration {

	@Autowired
	private Environment env;
	
	private String dataBaseUrlString; 
	
	@PostConstruct
	public void init() throws URISyntaxException
	{
		dataBaseUrlString = "jdbc:postgresql://" + dataBaseURL().getHost() + ":" + dataBaseURL().getPort() + dataBaseURL().getPath();
	}
	
	@Bean
	public URI dataBaseURL() throws URISyntaxException
	{
		return new URI(env.getProperty("DATABASE_URL"));
	}
	
	@Bean(destroyMethod = "close")
	public BasicDataSource dataSource() throws URISyntaxException
	{		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(dataBaseUrlString);
		dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
		dataSource.setUsername(dataBaseURL().getUserInfo().split(":")[0]);
		dataSource.setPassword(dataBaseURL().getUserInfo().split(":")[1]);
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("pl.zaprogramuj.webapplication");
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) throws URISyntaxException {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		txManager.setDataSource(dataSource());
		return txManager;
	}
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}
}
