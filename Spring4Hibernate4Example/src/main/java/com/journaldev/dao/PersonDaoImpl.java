package com.journaldev.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.journaldev.model.Person;

@Service
public class PersonDaoImpl implements PersonDao {
	private SessionFactory sessionFactory;

	@Autowired
	public PersonDaoImpl(
			@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Person person) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(person);
		tx.commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> list() {
		Session session = sessionFactory.openSession();
		List<Person> people = session.createQuery("from Person").list();
		session.close();

		return people;
	}
}
