package com.adam.bean;

import com.adam.constants.Constants;
import com.adam.model.Clazz;
import com.adam.model.Student;
import com.adam.model.StudentClass;
import com.adam.repository.clazz.ClazzRepository;
import com.adam.repository.student.StudentRepository;
import com.adam.repository.student.StudentRepositoryImpl;
import com.adam.repository.studentClazz.StudentClazzRepository;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Inject
    private StudentRepository studentRepository;

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

    public Converter monitorConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
                int monitorId = 0;
                try {
                    monitorId = Integer.parseInt(s);
                } catch (Exception e) {
                    System.out.println(e);
                    throw new ConverterException("Monitor's Id must be a number !");
                }

                return new StudentRepositoryImpl().findById(monitorId);
            }

            @Override
            public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
                Student monitor = (Student) o;
                return String.valueOf(monitor.getId());
            }
        };
    }

    public Validator monitorValidator() {
        return (facesContext, uiComponent, o) -> {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            Student student = (Student) o;
            EditableValueHolder editableValueHolder = (EditableValueHolder) uiComponent;
            if (student == null) {
                editableValueHolder.resetValue();
                message.setSummary("Don't have student's id");
                throw new ValidatorException(message);
            }
            List<StudentClass> list = clazz_instance.getListStudent();
            boolean isExist = false;
            for (StudentClass sc : list) {
                if (student.getId() == sc.getStudent().getId()) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                editableValueHolder.resetValue();
                message.setSummary("Monitor must be a member of the class");
                throw new ValidatorException(message);
            }
        };
    }

    public void addStudentToTheClass() {
        StudentClass studentClass = new StudentClass();
        studentClass.setClazz(clazz_instance);
        clazz_instance.getListStudent().add(studentClass);
        repo.update(clazz_instance.getId());
        System.out.println(clazz_instance.getListStudent());
    }

    public Converter addStudentToTheClassConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
                Student student = null;
                try {
                    int studentId = Integer.parseInt(s);
                    student = studentRepository.findById(studentId);
                } catch (Exception e) {
                    System.out.println(e);
                    throw new ConverterException("Student's ID must be a number !");
                }
                return student;
            }

            @Override
            public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
                Student student = (Student) o;
                return String.valueOf(student.getId());
            }
        };
    }

    public Validator addStudentClassValidator() {
        return (facesContext, uiComponent, o) -> {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            Student student = (Student) o;
            EditableValueHolder editableValueHolder = (EditableValueHolder) uiComponent;

            if (student == null) {
                editableValueHolder.resetValue();
                message.setSummary("Don't have student");
                throw new ValidatorException(message);
            }
            List<StudentClass> list = clazz_instance.getListStudent();
            List<Student> studentList_notNull = new ArrayList<>();
            for (StudentClass sc : list) {
                if (sc.getStudent() != null) {
                    studentList_notNull.add(sc.getStudent());
                }
            }
            for (Student s : studentList_notNull) {
                if (student.getId() == s.getId()) {
                    editableValueHolder.resetValue();
                    message.setSummary("Student alredy in the class");
                    throw new ValidatorException(message);
                }
            }
        };
    }

    public void deleteStudentClass(StudentClass studentClass) {
        clazz_instance.getListStudent().remove(studentClass);
        repo.update(clazz_instance.getId());
    }
}