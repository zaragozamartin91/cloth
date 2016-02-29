package cloth.service.user.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloth.model.User;
import cloth.service.user.UserNotFoundException;
import cloth.service.user.UserService;

@Service
public class DataUserService implements UserService {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}

		session.close();

		return user;
	}

	@Override
	public User getUserFromEmail(String email) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult();

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
		User user = (User) criteria.uniqueResult();

		return user == null;
	}
}
