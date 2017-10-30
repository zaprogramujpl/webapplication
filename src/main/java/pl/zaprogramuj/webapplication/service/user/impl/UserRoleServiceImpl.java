package pl.zaprogramuj.webapplication.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.zaprogramuj.webapplication.model.UserRoleEnum;
import pl.zaprogramuj.webapplication.model.user.UserRole;
import pl.zaprogramuj.webapplication.service.user.UserRoleService;

@Service("userRoleServiceImpl")
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserRoleServiceImpl implements UserRoleService
{

	@Autowired
	private UserRoleService userRoleDaoService;

	@Override
	public List<UserRole> findAll()
	{
		return userRoleDaoService.findAll();
	}

	@Override
	public UserRole findById(long id)
	{
		return userRoleDaoService.findById(id);
	}

	@Override
	public UserRole findByName(String name)
	{
		return userRoleDaoService.findByName(name);
	}

	@Override
	public UserRole findByEnumValue(UserRoleEnum enumValue)
	{
		return userRoleDaoService.findByEnumValue(enumValue);
	}
}
