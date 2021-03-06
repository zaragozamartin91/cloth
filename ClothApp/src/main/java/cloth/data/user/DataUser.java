package cloth.data.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import cloth.model.user.User;
import cloth.model.user.UserAccess;

@Entity
@Table(name = "users")
public class DataUser implements User {
	private String email;
	private String name;
	private Long dni;
	private String password;

	@Override
	@Id
	@Column(nullable = false, unique = true)
	@Email
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	@Column(nullable = false)
	public Long getDni() {
		return dni;
	}

	@Override
	public void setDni(Long dni) {
		this.dni = dni;
	}

	@Override
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataUser other = (DataUser) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserData [email=" + email + ", name=" + name + ", dni=" + dni + "]";
	}

	public DataUser() {
		super();
	}

	public DataUser(String email, String name, Long dni, String password) {
		super();
		this.email = email;
		this.name = name;
		this.dni = dni;
		this.password = password;
	}

	public DataUser(UserAccess userAccess) {
		this(userAccess.getEmail(), userAccess.getName(), userAccess.getDni(), userAccess.getPassword());
	}
}
