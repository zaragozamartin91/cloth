package cloth.web.user;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserRegisterForm {
	@NotNull(message = "{email.valid}")
	@Email(message = "{email.valid}")
	private String email;

	@NotNull(message = "{name.valid}")
	@Size(min = 2, max = 64, message = "{name.size}")
	private String name;

	@NotNull(message = "{dni.valid}")
	@DecimalMin(value = "1000000.00", message="{dni.size}")
	private Long dni;

	@NotNull
	@Size(min = 5, max = 25, message = "{password.size}")
	@Pattern(regexp = "[A-Z](\\d|\\w)+", message = "{password.regexp}")
	private String password;

	@NotNull
	@Size(min = 5, max = 25, message = "{password.size}")
	@Pattern(regexp = "[A-Z](\\d|\\w)+", message = "{password.regexp}")
	private String duplicatePassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDuplicatePassword() {
		return duplicatePassword;
	}

	public void setDuplicatePassword(String duplicatePassword) {
		this.duplicatePassword = duplicatePassword;
	}
}
