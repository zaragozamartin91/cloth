package hibernateexamples.mkyong;

import hibernateexamples.mkyong.onetomany.config.Config;
import hibernateexamples.mkyong.onetomany.model.Author;
import hibernateexamples.mkyong.onetomany.model.Book;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@ActiveProfiles("testMysql")
public class OneToManyMysqlPersistenceTest {
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