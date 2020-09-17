package com.adam.bean;

import com.adam.constants.Constants;
import com.adam.model.Student;
import com.adam.repository.student.StudentRepository;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class StudentBean implements Serializable {

    private boolean showStudentsDetail = false;
    private final String ID_DETAIL_STUDENT = Constants.SHOW_STUDENT_DETAIL;

    public String getID_DETAIL_STUDENT() {
        return ID_DETAIL_STUDENT;
    }

    private String view_ID = "";


    public Student getStudent_instance() {
        return student_instance;
    }

    public String getView_ID() {
        return view_ID;
    }

    public void setView_ID(String view_ID) {
        this.view_ID = view_ID;
    }

    public void setStudent_instance(Student student_instance) {
        this.student_instance = student_instance;
    }

    private Student student_instance;

    public boolean isShowStudentsDetail() {
        return showStudentsDetail;
    }

    public void setShowStudentsDetail(boolean showStudentsDetail) {
        this.showStudentsDetail = showStudentsDetail;
    }

    @Inject
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void newStudent() {
        Student student = new Student();
        save(student);
        getStudentDetail(student.getId());
        this.showStudentsDetail = true;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentDetail(int id) {
        this.student_instance = studentRepository.findById(id);
        this.view_ID = Constants.SHOW_STUDENT_DETAIL;
//        this.showStudentsDetail = true;
        return student_instance;
    }

    public void updateStudent(Student student) {
        studentRepository.edit(student);
        showStudentsDetail = false;
    }


    public void deleteStudent(int id) {
        studentRepository.delete(id);
        backToListStudent();
    }

    public void backToListStudent() {
        this.showStudentsDetail = false;
    }

    public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        FacesMessage message = new FacesMessage("");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        String name = value.toString().trim();
        if (name.length() < 4 || name.length() > 20) {
            message.setSummary("Invalid student's name");
        }
        if (!message.getSummary().equals("")) {
            throw new ValidatorException(message);
        }
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        FacesMessage message = new FacesMessage("");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        String email = value.toString().trim();
        String email_pattern = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

        if (email.length() < 4) {
            message.setSummary("Enter student's email");
        } else if (email.length() > 20) {
            message.setSummary("Invalid email. Enter name between 10-30 characters");
        } else if (!email.matches(email_pattern)) {
            message.setSummary("Invalid email");
        }

        if (!message.getSummary().equals("")) {
            throw new ValidatorException(message);
        }
    }

}
