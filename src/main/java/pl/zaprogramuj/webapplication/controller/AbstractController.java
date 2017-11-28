package pl.zaprogramuj.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.zaprogramuj.webapplication.service.system.SystemPropertiesService;
import pl.zaprogramuj.webapplication.service.user.UserRoleService;
import pl.zaprogramuj.webapplication.service.user.UserService;

public abstract class AbstractController
{
	@Autowired
	private SystemPropertiesService systemProperties;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Autowired
	@Qualifier("userRoleServiceImpl")
	private UserRoleService userRoleService;
	
	@Autowired
	@Qualifier("userFormValidator")
	private Validator userFormValidator;
	
	/*
	 * Although this method is private, this object will be added to views in classes inherited from the AbstractController.
	 */
	@ModelAttribute(name = "systemVersion")
	private String addSystemVersionToModel()
	{
		return systemProperties.getSystemVersion();
	}

	public SystemPropertiesService getSystemProperties()
	{
		return systemProperties;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public UserRoleService getUserRoleService()
	{
		return userRoleService;
	}

	public Validator getUserFormValidator()
	{
		return userFormValidator;
	}
}
