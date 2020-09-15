package com.adam.bean;

import com.adam.constants.Constants;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class ControllerBean implements Serializable {

    private final String ID_CLASS_MANAGEMENT = Constants.ID_CLASS_MANAGEMENT;
    private final String ID_STUDENT_MANAGEMENT = Constants.ID_STUDENT_MANAGEMENT;
    private String viewId;

    @Inject
    private Conversation conversation;

    @Inject
    private StudentBean studentBean;

    @Inject
    private ClazzBean clazzBean;


    public void showStudentManagement() {
        this.viewId = ID_STUDENT_MANAGEMENT;
        studentBean.startConversation();
    }

    public void showClassManagement() {
        this.viewId = ID_CLASS_MANAGEMENT;
        clazzBean.startConversation();
    }

    public String backToHomePage() {
        if (this.viewId.equals(ID_STUDENT_MANAGEMENT)) {
            studentBean.endConversation();
        } else if (this.viewId.equals(ID_CLASS_MANAGEMENT)) {
            clazzBean.endConversation();
        }
        this.viewId = null;
        return "/index?faces-redirect = true";
    }

    public String getID_CLASS_MANAGEMENT() {
        return ID_CLASS_MANAGEMENT;
    }

    public String getID_STUDENT_MANAGEMENT() {
        return ID_STUDENT_MANAGEMENT;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

}
