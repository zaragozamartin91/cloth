package com.mkyong;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkyong.config.Config;
import com.mkyong.model.Stock;
import com.mkyong.model.StockDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@ActiveProfiles("testMemory")
public class OneToOneInMemoryPersistenceTest {
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
		Stock stock = new Stock("code", "name");

		perform(session -> session.save(stock));
		perform(session -> {
			List<?> stocks = session.createQuery("from Stock").list();
			assertEquals(1, stocks.size());
			return stocks;
		});
		perform(session -> {
			List<?> stocks = session.createQuery(
					"from Stock s where s.code='code' and s.name='name'")
					.list();
			assertEquals(1, stocks.size());
			return stocks;
		});
		perform(session -> {
			Criteria criteria = session.createCriteria(Stock.class);
			criteria.add(Restrictions.eq("id", 1));
			List<?> stocks = criteria.list();
			assertEquals(1, stocks.size());
			return stocks;
		});

		perform(session -> session.save(new StockDetail(stock, "company",
				"desc", "remark", today())));

		perform(session -> {
			Stock __stock = (Stock) session.createQuery("from Stock").list()
					.get(0);
			assertNotNull(__stock.getDetail());
			return __stock;
		});

	}

	private Date today() {
		return new Date();
	}
}
