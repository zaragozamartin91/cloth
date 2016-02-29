package cloth.web.user;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cloth.model.User;
import cloth.service.user.UserService;
import cloth.service.user.impl.DummyUsersCreator;
import cloth.web.AbstractClothController;
import cloth.web.ClothController;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes(ClothController.CURRENT_USER_SESSION_ATTRIBUTE)
public class UserController extends AbstractClothController {
	@Autowired
	@Qualifier("dataUserService")
	UserService userService;

	/* Crea usuarios de debug. Unicamente trabaja con el perfil de spring 'test' */
	@Autowired(required = false)
	DummyUsersCreator dummyUsersCreator;

	@PostConstruct
	private void init() {
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		setDefaultUserLoginForm(model);
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@Valid UserLoginForm userLoginForm, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "login";
		}

		User currentUser;
		String userEmail = userLoginForm.getEmail();
		try {
			currentUser = userService.getUserFromEmail(userEmail);
		} catch (Exception e) {
			setDefaultUserLoginForm(model);
			setLoginErrorMessage(model, userEmail);
			return "login";
		}

		setCurrentUser(currentUser, model);

		return "redirect:/cloth";
	}

	private void setLoginErrorMessage(Model model, String userEmail) {
		model.addAttribute("loginErrorUserEmail", userEmail);
	}

	private void setDefaultUserLoginForm(Model model) {
		model.addAttribute("userLoginForm", new UserLoginForm());
	}
}
