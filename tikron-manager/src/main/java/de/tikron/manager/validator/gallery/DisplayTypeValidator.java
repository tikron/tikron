/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.validator.gallery;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import de.tikron.persistence.model.gallery.CategoryType;
import de.tikron.persistence.model.gallery.CategoryTypeId;
import de.tikron.persistence.model.gallery.DisplayType;

/**
 * Validates the display type of a category. 
 *
 * @date 07.04.2015
 * @author Titus Kruse
 */
@FacesValidator(value="displayTypeValidator")
public class DisplayTypeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// Cast the value of the selected display type
		DisplayType displayType = (DisplayType) value;
		
		// Obtain the component and submitted value of the category type
    UIInput categoryTypeComponent = (UIInput) component.getAttributes().get("categoryType");
//    String categoryTypeValue = (String) categoryTypeComponent.getSubmittedValue();
		CategoryType categoryType = (CategoryType) categoryTypeComponent.getValue();
    
    // Validate against each other
		if (categoryType != null && categoryType.getId().equals(CategoryTypeId.GALLERY) && displayType == null) {
    	((UIInput) component).setValid(false);
    	throw new ValidatorException(new FacesMessage("Display type required for category type: " + categoryType.getId()));
    }
	}
}
