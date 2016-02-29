package cloth.web;

import org.springframework.ui.Model;

import cloth.model.User;

public abstract class AbstractClothController implements ClothController {
	public boolean isCurrentUserPresent(Model model) {
		return model.containsAttribute(CURRENT_USER_SESSION_ATTRIBUTE);
	}

	public void setCurrentUser(User user, Model model) {
		model.addAttribute(CURRENT_USER_SESSION_ATTRIBUTE, user);
	}

	public AbstractClothController() {
	}
}
