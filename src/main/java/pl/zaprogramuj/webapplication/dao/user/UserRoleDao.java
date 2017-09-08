package pl.zaprogramuj.webapplication.dao.user;

import pl.zaprogramuj.webapplication.service.user.UserRoleService;

public interface UserRoleDao extends UserRoleService{
	
	public void initUserProfileRoles();

}
