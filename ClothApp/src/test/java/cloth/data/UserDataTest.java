package cloth.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cloth.model.User;

public class UserDataTest extends DataTest {

	@Test
	public void testSaveSingleUser() {
		User expectedUser = new UserData();
		expectedUser.setDni(12345L);
		expectedUser.setName("Martin");
		expectedUser.setEmail("zaragoza@gmail.com");

		perform(session -> session.save(expectedUser));
		perform(session -> {
			User actualUser = (User) session.createCriteria(User.class).list().get(0);
			assertEquals(expectedUser, actualUser);
			return actualUser;
		});
	}

}
