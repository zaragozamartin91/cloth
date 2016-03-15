package cloth.web.user;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cloth.model.user.UserAccess;
import cloth.service.user.UserService;
import cloth.service.user.impl.DummyUsersCreator;
import cloth.web.AbstractClothController;
import cloth.web.ClothController;
import cloth.web.ViewName;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes(ClothController.CURRENT_USER_SESSION_ATTRIBUTE)
public class UserController extends AbstractClothController {
	private static final String REDIRECT_HOME = ViewName.REDIRECT_HOME.id();
	private static final String LOGIN_VIEW_NAME = ViewName.LOGIN.id();

	@Autowired
	@Qualifier("dataUserService")
	UserService userService;

	/* Crea usuarios de debug. Unicamente trabaja con el perfil de spring 'test' */
	@Autowired(required = false)
	DummyUsersCreator dummyUsersCreator;

	@PostConstruct
	private void init() {
	}

	@RequestMapping(path = "/register", method = GET)
	public String register(Model model) {
		setDefaultUserRegisterForm(model);
		return LOGIN_VIEW_NAME;
	}

	@RequestMapping(path = "/register", method = POST)
	public String register(@Valid UserRegisterForm userRegisterForm, Errors errors, Model model) {
		if (errors.hasErrors()) {
			System.err.println("Errors detected in form running UserController#register");
			return LOGIN_VIEW_NAME;
		}

		
		
		return REDIRECT_HOME;
	}

	@RequestMapping(path = "/login", method = GET)
	public String login(Model model) {
		setDefaultUserLoginForm(model);
		return LOGIN_VIEW_NAME;
	}

	@RequestMapping(path = "/login", method = POST)
	public String login(@Valid UserLoginForm userLoginForm, Errors errors, Model model) {
		if (errors.hasErrors()) {
			System.err.println("Errors detected in form running UserController#login");
			return LOGIN_VIEW_NAME;
		}

		UserAccess currentUser;
		String userEmail = userLoginForm.getEmail();
		try {
			currentUser = userService.getUserFromEmail(userEmail);
			if (passwordNotValid(userLoginForm, currentUser)) {
				throw new RuntimeException("Password of " + currentUser.getEmail() + " not valid!");
			}
		} catch (Exception e) {
			setDefaultUserLoginForm(model);
			setLoginErrorMessage(model, userEmail);
			return LOGIN_VIEW_NAME;
		}

		setCurrentUser(currentUser, model);

		return REDIRECT_HOME;
	}

	private boolean passwordNotValid(UserLoginForm userLoginForm, UserAccess currentUser) {
		return !currentUser.getPassword().equals(userLoginForm.getPassword());
	}

	private void setLoginErrorMessage(Model model, String userEmail) {
		model.addAttribute("loginErrorUserEmail", userEmail);
	}

	private void setDefaultUserLoginForm(Model model) {
		model.addAttribute("userLoginForm", new UserLoginForm());
	}

	private void setDefaultUserRegisterForm(Model model) {
		model.addAttribute("userRegisterForm", new UserRegisterForm());
	}
}
