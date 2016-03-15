package cloth.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cloth.data.user.DataUser;
import cloth.model.user.User;
import cloth.model.user.UserAccess;

public class UserDataTest extends DataTest {

	@Test
	public void testSaveSingleUser() {
		User expectedUser = new DataUser();
		expectedUser.setDni(12345L);
		expectedUser.setName("Martin");
		expectedUser.setEmail("zaragoza@gmail.com");

		perform(session -> session.save(expectedUser));
		perform(session -> {
			UserAccess actualUser = (UserAccess) session.createCriteria(User.class).list().get(0);
			assertEquals(expectedUser, actualUser);
			return actualUser;
		});
	}

}
