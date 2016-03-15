package cloth.web;

public enum Views {
	REDIRECT_USER_LOGIN("redirect:/user/login"), REDIRECT_HOME("redirect:/cloth"), LOGIN("login"), HOME("home");

	private String id;

	public String id() {
		return id;
	}

	private Views(String id) {
		this.id = id;
	}
}
