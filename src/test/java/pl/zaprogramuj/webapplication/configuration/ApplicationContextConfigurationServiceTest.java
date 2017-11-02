package pl.zaprogramuj.webapplication.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.zaprogramuj.webapplication.dao.user.UserServiceDao;
import pl.zaprogramuj.webapplication.service.user.UserService;
import pl.zaprogramuj.webapplication.service.user.impl.UserServiceImpl;

@Configuration
public class ApplicationContextConfigurationServiceTest {

	@Bean(name = "mockitoUserDaoService")
	public UserServiceDao mockitoUserDaoService() {
		return Mockito.mock(UserServiceDao.class);
	}

	@Bean(name = "userServiceTest")
	public UserService getUserService() {
		return new UserServiceImpl();
	}

}
