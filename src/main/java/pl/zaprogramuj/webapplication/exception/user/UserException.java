package pl.zaprogramuj.webapplication.exception.user;

import pl.zaprogramuj.webapplication.exception.WebApplicationException;

public class UserException extends WebApplicationException{

	private static final long serialVersionUID = 1L;
	
	public UserException(String message) {
		super(message);
	}
}
