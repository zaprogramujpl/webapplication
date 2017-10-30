package pl.zaprogramuj.webapplication.dao.user.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import pl.zaprogramuj.webapplication.dao.AbstractDao;
import pl.zaprogramuj.webapplication.dao.user.UserRoleDao;
import pl.zaprogramuj.webapplication.model.UserRoleEnum;
import pl.zaprogramuj.webapplication.model.user.UserRole;

@Service("userRoleDaoImpl")
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserRoleDaoImpl extends AbstractDao<Long, UserRole> implements UserRoleDao{
	
	@Autowired
	@Qualifier("transactionManager")
	protected PlatformTransactionManager txManager;
	
	@PostConstruct
	private void init()
	{
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult()
		{
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status)
			{
				initUserProfileRoles();
			}
		});
	}
	
	@Override
	public void initUserProfileRoles() {
		UserRoleEnum[] rolesTable = UserRoleEnum.class.getEnumConstants();
		for (UserRoleEnum roleName : rolesTable)
		{
			UserRole r = findByName(roleName.getUserRole());
			if (r == null)
			{
				UserRole role = new UserRole();
				role.setRole(roleName.getUserRole());
				persist(role);
			}
		}
		
	}

	@Override
	public List<UserRole> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole findByName(String name) {
		Session session = getEntityMenager().unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserRole.class).add(Restrictions.eq("role", name));
		UserRole userRole = (UserRole) criteria.uniqueResult();
		return userRole;
	}

	@Override
	public UserRole findByEnumValue(UserRoleEnum enumValue) {
		return findByName(enumValue.getUserRole());
	}
}
