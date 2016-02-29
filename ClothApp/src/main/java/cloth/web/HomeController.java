package cloth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(path = { "/cloth", "/" })
@SessionAttributes({ ClothController.CURRENT_USER_SESSION_ATTRIBUTE })
public class HomeController extends AbstractClothController {
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		if (isCurrentUserPresent(model)) {
			System.out.println("Login user found!");
			return "home";
		}

		return "redirect:/user/login";
	}

}
