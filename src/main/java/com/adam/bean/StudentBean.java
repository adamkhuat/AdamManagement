package com.adam.bean;

import com.adam.constants.Constants;
import com.adam.model.Student;
import com.adam.repository.student.StudentRepository;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@ConversationScoped
public class StudentBean implements Serializable {

    private final String ID_DETAIL_STUDENT = Constants.SHOW_STUDENT_DETAIL;
    private final String ID_STUDENT_MANAGEMENT = Constants.ID_STUDENT_MANAGEMENT;
    private Student student_instance;

    public String getID_DETAIL_STUDENT() {
        return ID_DETAIL_STUDENT;
    }

    private String view_ID = ID_STUDENT_MANAGEMENT;

    public String getView_ID() {
        return view_ID;
    }

    public void setView_ID(String view_ID) {
        this.view_ID = view_ID;
    }

    public String getID_STUDENT_MANAGEMENT() {
        return ID_STUDENT_MANAGEMENT;
    }

    public Student getStudent_instance() {
        return student_instance;
    }

    public void setStudent_instance(Student student_instance) {
        this.student_instance = student_instance;
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
    }

    @PreDestroy
    public void testDestroy() {
        System.out.println("destroy");
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentDetail(int id) {
        this.student_instance = studentRepository.findById(id);
        this.view_ID = Constants.SHOW_STUDENT_DETAIL;
        return student_instance;
    }

    public void updateStudent(Student student) {
        studentRepository.edit(student);
    }

    public void deleteStudent(int id) {
        studentRepository.delete(id);
        backToListStudent();
    }

    public void backToListStudent() {
        this.view_ID = ID_STUDENT_MANAGEMENT;
    }

    public Validator validateStudentName() {
        return (facesContext, uiComponent, value) -> {
            FacesMessage message = new FacesMessage("");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            String name = value.toString().trim();
            if (name.length() < 1 || name.length() > 20) {
                message.setSummary("Invalid student's name");
            }
            if (!message.getSummary().equals("")) {
                throw new ValidatorException(message);
            }
        };
    }

    public Validator validatorStudentEmail() {
        return (facesContext, uiComponent, o) -> {
            FacesMessage message = new FacesMessage("");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            String email = o.toString().trim();
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
        };
    }

    public Validator validateDateOfBirth() {
        return (facesContext, uiComponent, o) -> {
            if (o == null) {
                throw new ValidatorException(new FacesMessage("Enter Date"));
            }
            FacesMessage message = new FacesMessage("");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);
            dateFormat.setLenient(false);
            String dateString = dateFormat.format(o);
            if (!dateString.matches(Constants.DATE_REGEX)) {
                message.setSummary("Wrong date for mat");
            } else {
                try {
                    dateFormat.parse(dateString);
                    message.setSummary("");
                } catch (Exception e) {
                    message.setSummary("Wrong date !");
                }
            }
            if (!message.getSummary().equals("")) {
                throw new ValidatorException(message);
            }
        };
    }

    public Converter dateOfBirthConverter() {

        return new Converter() {


            @Override
            public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
                SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_PATTERN);
                Date date = null;
                if (s.contains("-")) {
                    s = s.replace("-", "/");
                }
                try {
                    date = format.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }

            @Override
            public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
                SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_PATTERN);
                String value = format.format(o);
                return value;
            }
        };
    }


}
