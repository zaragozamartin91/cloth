package cloth.service.user;

import cloth.model.User;

/**
 * Facilita la manipulacion de usuarios.
 * 
 * @author martin
 *
 */
public interface UserService {

	User addUser(User user);

	User getUserFromEmail(String email);

	boolean validateUser(String email, String password);

}