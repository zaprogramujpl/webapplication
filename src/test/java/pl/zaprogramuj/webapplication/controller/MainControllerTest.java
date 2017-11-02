package pl.zaprogramuj.webapplication.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.zaprogramuj.webapplication.configuration.ApplicationContextConfigurationControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationControllerTest.class })
@WebAppConfiguration
public class MainControllerTest {
	
	@Test
	public void test()
	{
		
	}
}
