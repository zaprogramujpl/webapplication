package pl.zaprogramuj.webapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.webapplication.exception.user.UserExistsException;
import pl.zaprogramuj.webapplication.model.UserRoleEnum;
import pl.zaprogramuj.webapplication.model.form.user.UserProfileForm;
import pl.zaprogramuj.webapplication.model.user.UserProfile;
import pl.zaprogramuj.webapplication.service.system.SystemPropertiesService;
import pl.zaprogramuj.webapplication.service.user.UserRoleService;
import pl.zaprogramuj.webapplication.service.user.UserService;

@Controller
@RequestMapping(value = "/")
public class MainController
{
	@Autowired
	private SystemPropertiesService systemProperties;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Autowired
	@Qualifier("userRoleServiceImpl")
	private UserRoleService userRoleService;

	@ModelAttribute(name = "systemVersion")
	public String addSystemVersionToModel()
	{
		return systemProperties.getSystemVersion();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultPage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("index");
		return mnv;
	}

	// Login && Logout [BEGIN]
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("login/loginPage");
		return mnv;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	// Login && Logout [END]

	// REGISTER [BEGIN]
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerUserGET()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("userProfileForm", new UserProfileForm());
		mnv.setViewName("registerUser/registerUser");
		return mnv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUserPOST(Model model,
			@ModelAttribute("userProfileForm") @Validated UserProfileForm userProfileForm, BindingResult bindingResult)
	{
		ModelAndView mnv = new ModelAndView();

		if (bindingResult.hasErrors())
		{
			mnv.setViewName("registerUser/registerUser");
			return mnv;
		}

		try
		{
			UserProfile user = userProfileForm.getUser();
			user.addRole(userRoleService.findByEnumValue(UserRoleEnum.USER));
			userService.addUser(user);
		} catch (UserExistsException e)
		{
			mnv.addObject("userExistError", "userExistError");
			mnv.setViewName("registerUser/registerUser");
			return mnv;
		}

		mnv.setViewName("index");
		return mnv;
	}
	// REGISTER [END]
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("error/403");
		return mnv;
	}
}
