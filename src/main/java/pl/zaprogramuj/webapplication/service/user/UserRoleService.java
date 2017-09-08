package pl.zaprogramuj.webapplication.service.user;

import java.util.List;

import pl.zaprogramuj.webapplication.model.UserRoleEnum;
import pl.zaprogramuj.webapplication.model.user.UserRole;

public interface UserRoleService {
	
	public abstract List<UserRole> findAll();
	
	public abstract UserRole findById(long id);
	
	public abstract UserRole findByName(String name);
	
	public abstract UserRole findByEnumValue(UserRoleEnum enumValue);
}
