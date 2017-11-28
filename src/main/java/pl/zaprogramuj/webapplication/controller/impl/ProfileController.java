package pl.zaprogramuj.webapplication.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.webapplication.util.SystemViewsName;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView profilePage()
	{
		return new ModelAndView(SystemViewsName.USER_PROFILE);	
	}
}
