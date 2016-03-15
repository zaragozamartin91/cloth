package cloth.model.user;

/**
 * Identifica un usuario de la aplicacion.
 * 
 * @author martin
 *
 */
public interface User extends UserAccess {

	void setName(String name);

	void setDni(Long dni);

	void setEmail(String email);

	void setPassword(String password);

}