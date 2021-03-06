package cloth.service.user.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import cloth.data.user.DataUser;
import cloth.model.user.User;
import cloth.model.user.UserAccess;
import cloth.service.user.UserService;

/**
 * Crea usuarios dummy para probar la aplicacion.
 * 
 * @author martin.zaragoza
 *
 */
@Service
@DependsOn({ "dataUserService" })
@Profile("test")
public class DummyUsersCreator {
	@Autowired
	@Qualifier("dataUserService")
	private UserService userService;

	private UserAccess createUser(String email, String name, long dni, String password) {
		User user = new DataUser();
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
