package com.mkyong;

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

import com.mkyong.onetomany.config.Config;
import com.mkyong.onetomany.model.Author;
import com.mkyong.onetomany.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@ActiveProfiles("testMemory")
public class OneToManyInMemoryPersistenceTest {
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
		Author author = new Author("king");
		Book darkTowerBook = new Book("the dark tower", author);

		perform(session -> session.save(author));
		perform(session -> session.save(darkTowerBook));

		perform(session -> {
			Book book = (Book) session.createCriteria(Book.class).list().get(0);
			assertEquals(author.getId(), book.getAuthor().getId());
			return book;
		});
	}
}