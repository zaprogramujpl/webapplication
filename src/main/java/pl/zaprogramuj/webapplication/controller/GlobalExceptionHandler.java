package pl.zaprogramuj.webapplication.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import pl.zaprogramuj.webapplication.util.SystemViewsName;

@ControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ModelAndView errorHandling404()
	{
		return new ModelAndView(SystemViewsName.REDIRECT_TO_MAIN_PAGE);
	}
}
