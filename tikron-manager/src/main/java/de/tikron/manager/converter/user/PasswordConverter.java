package de.tikron.manager.converter.user;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@FacesConverter("passwordConverter")
public class PasswordConverter implements Converter {

	public static final String SUBSTITUTION = "********";

	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static void main(String[] args) {
		System.out.println("[" + passwordEncoder.encode("Eopl12Muke") + "]");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.length() == 0)
			return null;
		else if (value.equals(SUBSTITUTION))
			return value;
		return passwordEncoder.encode(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null)
			return "";
		return SUBSTITUTION;
	}

}
