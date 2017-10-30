package pl.zaprogramuj.webapplication.dao.user.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pl.zaprogramuj.webapplication.dao.AbstractDao;
import pl.zaprogramuj.webapplication.dao.user.UserServiceDao;
import pl.zaprogramuj.webapplication.model.user.UserProfile;

@Repository("userServiceDaoImpl")
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserServiceDaoImpl extends AbstractDao<Long, UserProfile> implements UserServiceDao {	

	@Override
	public UserProfile findUserById(long id_user) {
		return getByPrimaryKey(id_user);
	}

	@Override
	public UserProfile findUserByLogin(String userLogin) {		
		Session session = getEntityMenager().unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserProfile.class).add(Restrictions.eq("login", userLogin));
		UserProfile user = (UserProfile) criteria.uniqueResult();
		return user;
	}

	@Override
	public void addUser(UserProfile user) {
		persist(user);
	}
}
