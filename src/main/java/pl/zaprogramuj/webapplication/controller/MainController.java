package pl.zaprogramuj.webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class MainController {

	@RequestMapping
	public ModelAndView defaultPage()
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("index");
		return mnv;
	}
}
