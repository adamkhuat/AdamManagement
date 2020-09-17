package com.adam.bean;

import com.adam.model.Clazz;
import com.adam.model.Student;
import com.adam.model.StudentClass;
import com.adam.repository.clazz.ClazzRepository;
import com.adam.repository.studentClazz.StudentClazzRepository;

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
    private StudentClazzRepository studentClazzRepository;

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
        this.setShowClazzDetail(false);
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

    public List<Student> getStudentList(int clazzId) {
        return studentClazzRepository.getListStudent(clazzId);
    }

    public void createStudentClazz(){
        StudentClass studentClass = new StudentClass();
        studentClazzRepository.save(studentClass);
        getStudentClazzDetail(studentClass.getId());
    }

    public StudentClass getStudentClazzDetail(int id){
        StudentClass studentClass = studentClazzRepository.findStudentInTheClassById(id);
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("StudentClazzDetail", studentClass);
        return studentClass;
    }

}
