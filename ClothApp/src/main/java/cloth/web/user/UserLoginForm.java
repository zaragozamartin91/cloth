package cloth.web.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserLoginForm {
	@NotNull(message = "{email.valid}")
	@Email(message = "{email.valid}")
	private String email;
	
	@NotNull
	@Size(min = 5, max = 25, message = "{password.size}")
	@Pattern(regexp = "[A-Z](\\d|\\w)+" , message = "{password.regexp}")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
