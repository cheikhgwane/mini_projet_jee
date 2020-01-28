package utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import beans.Client;

public class FormValidator {

	private HashMap<String, String> errors = new HashMap<String, String>();
	private static String message;
	private static String ADMIN_USERNAME = "dante";
	private static String ADMIN_PWD = "inferno123";
	private String fields[] = { "nom", "prenom", "adresse", "telephone" };
	private Client client;

	private HttpServletRequest request;

	public FormValidator(HttpServletRequest request) {
		this.request = request;
	}

	public boolean validateForm() {
		validateFields(fields);
		if (errors.isEmpty()) {
			message = "Client added";
			return true;
		}
		message = "error while adding client! Please retry";
		return false;
	}

	public HashMap<String, String> getError() {
		return this.errors;
	}

	public String getMessage() {
		return message;
	}

	public Client getClient() {
		client = new Client();
		client.setNom(getValue("nom"));
		client.setPrenom(getValue("prenom"));
		client.setAdresse(getValue("adresse"));
		client.setTelephone(getValue("telephone"));
		return client;
	}

	public boolean authentificateUser() {
		String login = getValue("login");
		String password = getValue("password");
		if (ADMIN_USERNAME.equals(login) && ADMIN_PWD.equals(password)) {
			return true;
		}
		return false;
	}

	private String getValue(String param) {
		return request.getParameter(param) != null ? request.getParameter(param) : null;
	}

	private void validateFields(String... fields) {
		for (String f : fields) {
			if (getValue(f) == null || getValue(f).length()==0) {
				errors.put(f, "Required field");
			}
		}
	}

}
