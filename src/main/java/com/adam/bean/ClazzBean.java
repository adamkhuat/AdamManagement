package com.adam.bean;

import com.adam.model.Clazz;
import com.adam.repository.clazz.ClazzRepository;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class ClazzBean implements Serializable {

    @Inject
    private ClazzRepository repo;

    @Inject
    private Conversation conversation;

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

    public List<Clazz> getAllClazz(){
        return repo.getAllClazz();
    }

}
