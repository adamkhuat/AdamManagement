package com.adam.converter;

import com.adam.model.Student;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {

    private static final String datePattern = "yyyy-MM-dd";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return null;
    }
}
