package com.adam.validator.student;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "studentsNameValidator")
public class StudentsNameValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String sname = o.toString().trim();
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (sname.length() <= 0) {
            message.setSummary("Name is empty. Please enter student's name");
            throw new ValidatorException(message);
        } else if (sname.length() > 20) {
            message.setSummary("Name shouldn't longer than 20 symbols");
            throw new ValidatorException(message);
        }

    }
}
