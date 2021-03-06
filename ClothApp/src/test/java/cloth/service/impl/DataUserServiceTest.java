package cloth.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cloth.config.data.DataConfig;
import cloth.config.service.ServiceConfig;
import cloth.data.user.DataUser;
import cloth.model.user.User;
import cloth.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataConfig.class, ServiceConfig.class })
@ActiveProfiles("test")
public class DataUserServiceTest {
	@Autowired
	@Qualifier("dataUserService")
	UserService userService;

	@Test
	public void testGetUserFromEmail() {
		String email = "nicolas@sibi.com";

		User user = new DataUser();
		user.setEmail(email);
		user.setDni(1234L);
		user.setName("nicolas");
		user.setPassword("1234");

		userService.addUser(user);
		assertEquals( user ,  userService.getUserFromEmail(email) );
	}

}
