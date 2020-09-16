package com.adam.bean;

import com.adam.model.Student;
import com.adam.repository.student.StudentRepository;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ConversationScoped
public class StudentBean implements Serializable {

    private boolean showStudentsDetail = false;

    public boolean isShowStudentsDetail() {
        return showStudentsDetail;
    }

    public void setShowStudentsDetail(boolean showStudentsDetail) {
        this.showStudentsDetail = showStudentsDetail;
    }

    @Inject
    private StudentRepository studentRepository;

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

    public void getStudentDetail(int id) {
        Student student = studentRepository.findById(id);
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        map.put("studentDetail", student);
        this.showStudentsDetail = true;
    }

    public void updateStudent(Student student) {
        studentRepository.edit(student);
        showStudentsDetail = false;
    }


    public void deleteStudent(int id) {
        studentRepository.delete(id);
        backToListStudent();
    }

    public Student findStudentById(int id){
        return studentRepository.findById(id);
    }

    public void backToListStudent() {
        this.showStudentsDetail = false;
    }
}
