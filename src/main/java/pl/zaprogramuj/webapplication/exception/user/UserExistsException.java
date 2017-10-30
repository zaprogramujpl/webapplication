package pl.zaprogramuj.webapplication.exception.user;

import pl.zaprogramuj.webapplication.exception.WebApplicationException;

public class UserExistsException extends WebApplicationException{

	private static final long serialVersionUID = 1L;
		
	public UserExistsException(String message) {
		super(message);
	}

}
