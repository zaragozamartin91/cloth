package cloth.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cloth.model.User;
import cloth.service.UserNotFoundException;
import cloth.service.UserService;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes(ClothController.CURRENT_USER_SESSION_ATTRIBUTE)
public class UserController extends AbstractClothController {
	@Autowired
	@Qualifier("dataUserService")
	UserService userService;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(new UserLoginForm());
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@Valid UserLoginForm userLoginForm, Errors errors, RedirectAttributes model) {
		if (errors.hasErrors()) {
			return "login";
		}

		User currentUser = userService.getUserFromEmail(userLoginForm.getEmail());
		setCurrentUser(currentUser, model);

		return "redirect:/cloth";
	}

	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundHandler() {
		System.err.println("User not found!");
		return "login";
	}
}
