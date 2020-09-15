package com.adam.validator.student;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class StudentPasswordValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String spassword = o.toString().trim();
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (spassword.length() <= 0) {
            message.setSummary("Password is empty. Please enter student's Password");
            throw new ValidatorException(message);
        } else if (spassword.length() > 20) {
            message.setSummary("Password shouldn't longer than 20 symbols");
            throw new ValidatorException(message);
        }

    }
}
