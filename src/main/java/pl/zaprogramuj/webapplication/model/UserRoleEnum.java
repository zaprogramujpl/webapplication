package pl.zaprogramuj.webapplication.model;

public enum UserRoleEnum {

	USER("USER"), ADMIN("ADMIN");

	private String userRole;

	private UserRoleEnum(String userRole) {
		this.userRole = userRole;
	}

	public String getUserRole() {
		return userRole;
	}

}
