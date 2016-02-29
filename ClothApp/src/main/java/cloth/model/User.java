package cloth.model;

/**
 * Identifica un usuario de la aplicacion.
 * 
 * @author martin
 *
 */
public interface User {

	String getName();

	Long getDni();

	void setName(String name);

	void setDni(Long dni);

	String getEmail();

	void setEmail(String email);

	String getPassword();

	void setPassword(String password);

}