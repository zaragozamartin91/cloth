package cloth.service.user;

import cloth.model.user.UserAccess;

/**
 * Facilita la manipulacion de usuarios.
 * 
 * @author martin
 *
 */
public interface UserService {

	/**
	 * Agrega un usuario nuevo.
	 * 
	 * @param user
	 *            - Usuario a agregar.
	 * @return Usuario agregado.
	 */
	UserAccess addUser(UserAccess user);

	/**
	 * Recupera un usuario a partir de su email.
	 * 
	 * @param email
	 *            - Email de usuario.
	 * @return Usuario encontrado.
	 * @throws UserNotFoundException
	 *             - En caso de no encontrar al usuario especificado.
	 */
	UserAccess getUserFromEmail(String email);

	/**
	 * Valida las credenciales de un usuario.
	 * 
	 * @param email
	 *            - Email de usuario.
	 * @param password
	 *            - Password.
	 * @return true en caso de ser valido, false caso contrario.
	 */
	boolean validateUser(String email, String password);

}