package com.mkyong;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkyong.manytomany.config.Config;
import com.mkyong.manytomany.model.Employee;
import com.mkyong.manytomany.model.Meeting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@ActiveProfiles("testMysql")
public class ManyToManyMysqlPersistenceTest {
	@Autowired
	private SessionFactory sessionFactory;

	static interface TxAction {
		Object act(Session session);
	}

	private Object perform(TxAction action, Object... args) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Object result = null;

		try {
			result = action.act(session);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}

		session.close();
		return result;
	}

	@Test
	public void testSave() {
		Employee martin = new Employee("Martin");
		Meeting meeting = new Meeting("Programming", today());

		perform(session -> session.save(martin));
		perform(session -> session.save(meeting));

		perform(session -> {
			Employee employee = (Employee) session.createQuery("from Employee")
					.list().get(0);
			employee.setMeetings(newMeetingSet(meeting));
			session.update(employee);
			return employee;
		});
	}

	private Set<Employee> newEmployeeSet(Employee... employees) {
		return new HashSet<Employee>(Arrays.asList(employees));
	}

	private Set<Meeting> newMeetingSet(Meeting... meetings) {
		return new HashSet<Meeting>(Arrays.asList(meetings));
	}

	private Date today() {
		return new Date();
	}
}