package pl.zaprogramuj.webapplication.service.user.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.zaprogramuj.webapplication.configuration.ApplicationContextConfigurationServiceTest;
import pl.zaprogramuj.webapplication.dao.user.UserServiceDao;
import pl.zaprogramuj.webapplication.exception.user.UserExistsException;
import pl.zaprogramuj.webapplication.exception.user.UserNotFoundException;
import pl.zaprogramuj.webapplication.model.user.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationServiceTest.class })
public class UserServiceImplTest 
{
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private UserServiceDao userDao;
	
	@BeforeClass
	public static void setUp()
	{

	}
	
	@Before
	public void before()
	{
		Mockito.reset(userDao);
	}
	
	//Tests for method : addUser [BEGIN]
	@Test
	public void shouldAddUser() throws UserExistsException
	{
		UserProfile user = new UserProfile();
		user.setId(100);
		user.setLogin("testUser");
		
		when(userDao.findUserByLogin(user.getLogin())).thenReturn(null);
		
		userService.addUser(user);
	}
	
	@Test(expected = UserExistsException.class)
	public void shouldThrowExceptionWhenUserExist() throws UserExistsException
	{
		UserProfile user = new UserProfile();
		user.setId(100);
		user.setLogin("testUser");
		
		when(userDao.findUserByLogin(user.getLogin())).thenReturn(user);
		
		userService.addUser(user);		
	}		
	//Tests for method : addUser [END]
	
	//Tests for method : findUserByLogin [BEGIN]
	@Test
	public void shouldFindUserByLogin()
	{
		UserProfile user = new UserProfile();
		user.setId(100);
		user.setLogin("testUser");
		when(userDao.findUserByLogin(user.getLogin())).thenReturn(user);
		
		assertEquals(user, userService.findUserByLogin(user.getLogin()));
	}
	
	@Test
	public void shouldReturnNullWhenThereIsNoUserWithGivenLogin()
	{
		UserProfile user = new UserProfile();
		user.setLogin("testUser");
		when(userDao.findUserByLogin(user.getLogin())).thenReturn(null);
		
		assertEquals(null, userService.findUserByLogin(user.getLogin()));
	}	
	//Tests for method : findUserByLogin [END]
	
	//Tests for method : findUserById [BEGIN]	
	@Test
	public void shouldFindUserWithGivenId() throws UserNotFoundException
	{
		UserProfile user = new UserProfile();
		user.setId(1);
		
		when(userDao.findUserById(user.getId())).thenReturn(user);
		
		assertEquals(user, userService.findUserById(user.getId()));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void shouldThrowExceptionWhenThereIsNotUserWithGivenId() throws UserNotFoundException
	{
		UserProfile user = new UserProfile();
		user.setId(1);
		
		when(userDao.findUserById(user.getId())).thenReturn(null);
		
		userService.findUserById(user.getId());
	}
	//Tests for method : findUserById [END]
	
	//Tests for method : template [BEGIN]	
	//Tests for method : template [END]
}
