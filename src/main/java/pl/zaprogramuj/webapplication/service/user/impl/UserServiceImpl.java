package pl.zaprogramuj.webapplication.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.webapplication.dao.user.UserServiceDao;
import pl.zaprogramuj.webapplication.exception.user.UserExistsException;
import pl.zaprogramuj.webapplication.exception.user.UserNotFoundException;
import pl.zaprogramuj.webapplication.model.user.UserProfile;
import pl.zaprogramuj.webapplication.service.user.UserService;

@Service("userServiceImpl")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserServiceDao userServiceDao;

	@Override
	public UserProfile findUserById(long idUser) throws UserNotFoundException
	{	
		UserProfile user = userServiceDao.findUserById(idUser);
		
		if(user == null)
		{
			throw new UserNotFoundException(idUser);
		}		
		
		return userServiceDao.findUserById(idUser);
	}

	@Override
	public UserProfile findUserByLogin(String userLogin)
	{
		return userServiceDao.findUserByLogin(userLogin);
	}

	@Override
	public void addUser(UserProfile newUser) throws UserExistsException
	{
		UserProfile userProfile = findUserByLogin(newUser.getLogin());

		if (userProfile != null)
		{
			throw new UserExistsException("There is already a user with an " + newUser.getLogin() + " login");
		}

		userServiceDao.addUser(newUser);
	}
}
