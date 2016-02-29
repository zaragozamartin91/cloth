package cloth.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cloth.config.data.DataConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataConfig.class })
@ActiveProfiles("test")
public class DataTest {
	@Autowired
	protected SessionFactory sessionFactory;

	protected static interface TxAction {
		Object act(Session session);
	}

	/**
	 * Ejecuta una accion a realizar luego de abrir una sesion y antes de
	 * realizar un commit de una transaccion.
	 * 
	 * @param action
	 *            - Accion a realizar despues de abrir una sesion, iniciar una
	 *            transaccion y antes de commitear una transaccion.
	 * @return resultado de la accion.
	 */
	protected Object perform(TxAction action) {
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
}
