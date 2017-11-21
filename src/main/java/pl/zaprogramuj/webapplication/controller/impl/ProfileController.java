package pl.zaprogramuj.webapplication.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView profilePage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("userProfile/userProfile");
		return mnv;
	}
}
