package cloth.service.user.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import cloth.data.UserData;
import cloth.model.User;
import cloth.service.user.UserService;

@Service
@DependsOn({ "dataUserService" })
@Profile("test")
public class DummyUsersCreator {
	@Autowired
	@Qualifier("dataUserService")
	private UserService userService;

	private User createUser(String email, String name, long dni, String password) {
		User user = new UserData();
		user.setDni(dni);
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);

		return user;
	}

	@PostConstruct
	private void init() {
		userService.addUser(createUser("martin@zaragoza.com", "martin", 35657201L, "Martin"));
		userService.addUser(createUser("mateo@zaragoza.com", "mateo", 12345678L, "Mateo"));
	}

	public DummyUsersCreator() {
	}
}
