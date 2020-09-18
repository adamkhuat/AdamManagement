package com.adam.bean;

import com.adam.constants.Constants;
import com.adam.model.Clazz;
import com.adam.model.Student;
import com.adam.repository.clazz.ClazzRepository;
import com.adam.repository.student.StudentRepositoryImpl;
import com.adam.repository.studentClazz.StudentClazzRepository;

import javax.enterprise.context.ConversationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class ClazzBean implements Serializable {

    private final String ID_CLASS_MANAGEMENT = Constants.ID_CLASS_MANAGEMENT;
    private final String SHOW_CLASS_DETAIL = Constants.SHOW_CLASS_DETAIL;
    private String view_Id = ID_CLASS_MANAGEMENT;
    private Clazz clazz_instance;

    public Clazz getClazz_instance() {
        return clazz_instance;
    }

    public void setClazz_instance(Clazz clazz_instance) {
        this.clazz_instance = clazz_instance;
    }

    public String getView_Id() {
        return view_Id;
    }

    public void setView_Id(String view_Id) {
        this.view_Id = view_Id;
    }

    public String getID_CLASS_MANAGEMENT() {
        return ID_CLASS_MANAGEMENT;
    }

    public String getSHOW_CLASS_DETAIL() {
        return SHOW_CLASS_DETAIL;
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
    }

    public void save(Clazz clazz) {
        repo.save(clazz);
    }

    public void update(int id) {
        repo.update(id);
        this.setView_Id(SHOW_CLASS_DETAIL);
    }

    public void delete(int id) {
        repo.delete(id);
        this.setView_Id(ID_CLASS_MANAGEMENT);
    }

    public Clazz getClazzDetail(int id) {
        this.clazz_instance = repo.findClazzById(id);
        this.setView_Id(SHOW_CLASS_DETAIL);
        return clazz_instance;
    }

    public void backToClazzList() {
        this.setView_Id(ID_CLASS_MANAGEMENT);
    }

    public List<Student> getStudentList(int clazzId) {
        return studentClazzRepository.getListStudent(clazzId);
    }

    public Converter monitorConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
                int monitorId = Integer.parseInt(s);
                Student monitor = new StudentRepositoryImpl().findById(monitorId);
                return monitor;
            }

            @Override
            public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
                return String.valueOf(o);
            }
        };
    }


}
