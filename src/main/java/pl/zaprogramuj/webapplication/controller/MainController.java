package pl.zaprogramuj.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping
	public ModelAndView defaultPage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("index");
		return mnv;
	}
}
