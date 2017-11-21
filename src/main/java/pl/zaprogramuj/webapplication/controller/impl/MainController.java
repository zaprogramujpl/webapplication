package pl.zaprogramuj.webapplication.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.webapplication.controller.AbstractController;
import pl.zaprogramuj.webapplication.exception.user.UserExistsException;
import pl.zaprogramuj.webapplication.model.UserRoleEnum;
import pl.zaprogramuj.webapplication.model.form.user.UserProfileForm;
import pl.zaprogramuj.webapplication.model.user.UserProfile;
import pl.zaprogramuj.webapplication.util.SystemViewsName;

@Controller
@RequestMapping(value = "/")
public class MainController extends AbstractController
{
	@InitBinder
	private void initBinding(WebDataBinder binder)
	{
		binder.setValidator(userFormValidator);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultPage()
	{
		return new ModelAndView(SystemViewsName.INDEX);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage()
	{
		return new ModelAndView(SystemViewsName.LOGIN_PAGE);
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

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerUserGET()
	{
		ModelAndView mnv = new ModelAndView(SystemViewsName.REGISTER_USER);
		mnv.addObject("userProfileForm", new UserProfileForm());
		return mnv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUserPOST(Model model, @ModelAttribute("userProfileForm") @Validated UserProfileForm userProfileForm, BindingResult bindingResult)
	{
		ModelAndView mnv = new ModelAndView();

		if (bindingResult.hasErrors())
		{
			mnv.setViewName(SystemViewsName.REGISTER_USER);
			return mnv;
		}

		try
		{
			registerUser(userProfileForm);
			mnv.setViewName(SystemViewsName.INDEX);
		} catch (UserExistsException e)
		{
			mnv.addObject("userExistError", "userExistError");
			mnv.setViewName(SystemViewsName.REGISTER_USER);
		}

		return mnv;
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName(SystemViewsName.ERROR_403);
		return mnv;
	}

	private void registerUser(UserProfileForm userProfileForm) throws UserExistsException
	{
		UserProfile user = userProfileForm.getUser();
		user.addRole(userRoleService.findByEnumValue(UserRoleEnum.USER));
		userService.addUser(user);
	}
}
