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
	protected SystemPropertiesService systemProperties;

	@Autowired
	@Qualifier("userServiceImpl")
	protected UserService userService;

	@Autowired
	@Qualifier("userRoleServiceImpl")
	protected UserRoleService userRoleService;
	
	@Autowired
	@Qualifier("userFormValidator")
	protected Validator userFormValidator;
	
	/*
	 * Although this method is private, this object will be added to views in classes inherited from the AbstractController.
	 */
	@ModelAttribute(name = "systemVersion")
	private String addSystemVersionToModel()
	{
		return systemProperties.getSystemVersion();
	}
}
