package de.tikron.manager.converter.user;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@FacesConverter("passwordConverter")
public class PasswordConverter implements Converter {

	public static final String SUBSTITUTION = "********";

	private static ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent,
	 * java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.length() == 0)
			return null;
		else if (value.equals(SUBSTITUTION))
			return value;
		return passwordEncoder.encodePassword(value, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent,
	 * java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null)
			return "";
		return SUBSTITUTION;
	}

}
