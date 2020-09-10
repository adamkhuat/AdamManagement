package com.adam.bean;

import com.adam.constants.Constants;

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


    public void showStudentManagement() {
        this.viewId = ID_STUDENT_MANAGEMENT;
        startConversation();
    }

    public void showClassManagement() {
        this.viewId = ID_CLASS_MANAGEMENT;
        startConversation();
    }

    public void backToHomePage() {
        this.viewId = null;
        endConversation();
    }


    public void startConversation() {
        if (FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public void hello() {
        System.out.println("Hello");
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
