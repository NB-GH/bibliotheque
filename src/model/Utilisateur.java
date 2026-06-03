package model;

public class Utilisateur {
	private String login;
	private String password;
	private String role;
	
	
	public Utilisateur(String login, String password, String role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public static Utilisateur sansRole(String login, String password) {
		return new Utilisateur(login, password, null);
	}

	public static Utilisateur sansPassword(String login, String role) {
		return new Utilisateur(login, null, role);
	}
	
	public String getLogin() { return login; }
	public String getPassword() { return password; }
	public String getRole() { return role; }

}
