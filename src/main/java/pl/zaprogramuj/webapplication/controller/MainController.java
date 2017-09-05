package pl.zaprogramuj.webapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.webapplication.service.system.SystemPropertiesService;

@Controller
@RequestMapping(value = "/")
public class MainController {
	
	@Autowired
	private SystemPropertiesService systemProperties;
	
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
	
	//Login && Logout [BEGIN]
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("login/loginPage");
		return mnv;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	//Login && Logout [END]
	
	//UserProfile [BEGIN]
	//TODO:Do usuniêcia - nale¿y przenieœæ do nowego kontrolera
	@RequestMapping(value = "/profile/", method = RequestMethod.GET)
	public ModelAndView profilePage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("userProfile/userProfile");
		return mnv;
	}	
	//UserProfile [END]
	
	@RequestMapping(value="/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("error/403");
		return mnv;
	}
}
