package cloth.service.user.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloth.data.user.DataUser;
import cloth.model.user.User;
import cloth.model.user.UserAccess;
import cloth.service.user.UserNotFoundException;
import cloth.service.user.UserService;

/**
 * Servicio de usuarios que hace uso de la base de datos.
 * 
 * @author martin.zaragoza
 *
 */
@Service
public class DataUserService implements UserService {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public UserAccess addUser(UserAccess userAccess) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.save( new DataUser(userAccess) );
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}

		session.close();

		return userAccess;
	}

	@Override
	public UserAccess getUserFromEmail(String email) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		UserAccess user = (UserAccess) criteria.uniqueResult();

		if (user == null) {
			throw new UserNotFoundException("User " + email + " not found!");
		}

		return user;
	}

	@Override
	public boolean validateUser(String email, String password) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password", password));
		UserAccess user = (UserAccess) criteria.uniqueResult();

		return user == null;
	}
}
