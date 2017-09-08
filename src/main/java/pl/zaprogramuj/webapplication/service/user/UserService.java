package pl.zaprogramuj.webapplication.service.user;

import pl.zaprogramuj.webapplication.exception.user.UserExistsException;
import pl.zaprogramuj.webapplication.model.user.UserProfile;

/**
 * @author MichalK92
 *
 */
public interface UserService {
	
	/**
	 * This method try find user by his id. If user doesn't exist, method return null
	 * 
	 * @param id_user
	 * @return
	 */
	public abstract UserProfile findUserById(long id_user);
	
	/**
	 * This method try find user by his login. If user doesn't exist, method return null
	 * 
	 * @param id_user
	 * @return
	 */
	public abstract UserProfile findUserByLogin(String userLogin);	
	
	/**
	 * This method try add user to repository.
	 * 
	 * @param user
	 * @throws UserExistsException 
	 */
	public abstract void addUser(UserProfile user) throws UserExistsException;
}
