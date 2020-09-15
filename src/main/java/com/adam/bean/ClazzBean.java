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
import java.util.Map;

@Named
@ConversationScoped
public class ClazzBean implements Serializable {

    private boolean showClazzDetail = false;

    public boolean isShowClazzDetail() {
        return showClazzDetail;
    }

    public void setShowClazzDetail(boolean showClazzDetail) {
        this.showClazzDetail = showClazzDetail;
    }

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

    public List<Clazz> getAllClazz() {
        List<Clazz> list = repo.getAllClazz();
        System.out.println("CLASS SIZE" + list.size());
        return repo.getAllClazz();
    }

    public void createClazz() {
        Clazz clazz = new Clazz();
        save(clazz);
        getClazzDetail(clazz.getId());
        this.setShowClazzDetail(true);
    }

    public void save(Clazz clazz) {
        repo.save(clazz);
    }

    public void update(Clazz clazz) {
        repo.update(clazz);
        this.setShowClazzDetail(false);
    }

    public void delete(int id) {
        repo.delete(id);
    }

    public Clazz getClazzDetail(int id) {
        Clazz clazz = repo.findClazzById(id);
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        map.put("clazzDetail", clazz);
        this.setShowClazzDetail(true);
        return clazz;
    }

    public void backToClazzList() {
        this.setShowClazzDetail(false);
    }


}
