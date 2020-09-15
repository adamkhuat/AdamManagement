package com.adam.validator.student;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesValidator
public class DateOfBirthValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String datePattern = "yyyy-MM-dd";
        Date dateOfBirth = null;
        try {
            dateOfBirth = new SimpleDateFormat(datePattern).parse(o.toString());
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            long year = dateOfBirth.getYear();
            int month = dateOfBirth.getMonth();
            int day = dateOfBirth.getDate();

            if (dateOfBirth.getYear() > 2020 || dateOfBirth.getYear() < 0) {
                message.setSummary("Invalid Date of Birth. Please enter a true YEAR");
            }
            if (dateOfBirth.getMonth() < 0 || dateOfBirth.getMonth() > 12) {
                message.setSummary("Invalid Date of Birth. Please enter 0 < month < 12");
            }
            if (dateOfBirth.getDay() < 0 || dateOfBirth.getDay() > 31) {
                message.setSummary("Invalid Date of Birth. Please enter 0 < day < 31");
            }

            if (!message.getSummary().equals("")) {
                throw new ValidatorException(message);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
