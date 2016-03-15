package cloth.web;

public enum ViewName {
	REDIRECT_USER_LOGIN("redirect:/user/login"), REDIRECT_HOME("redirect:/cloth"), LOGIN("login"), HOME("home");

	private String id;

	public String id() {
		return id;
	}

	private ViewName(String id) {
		this.id = id;
	}
}
