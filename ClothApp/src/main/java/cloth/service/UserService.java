package cloth.service;

import cloth.model.User;

public interface UserService {

	User addUser(User user);

	User getUserFromEmail(String email);

}