package cloth.web;

import org.springframework.ui.Model;

import cloth.model.user.UserAccess;

public abstract class AbstractClothController implements ClothController {
	public boolean isCurrentUserPresent(Model model) {
		return model.containsAttribute(CURRENT_USER_SESSION_ATTRIBUTE);
	}

	public void setCurrentUser(UserAccess user, Model model) {
		model.addAttribute(CURRENT_USER_SESSION_ATTRIBUTE, user);
	}

	public AbstractClothController() {
	}
}
