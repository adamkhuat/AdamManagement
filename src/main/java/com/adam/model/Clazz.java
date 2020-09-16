package com.adam.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clazz")
@NamedQuery(query = "SELECT c FROM Clazz c", name = "getAllClazz")
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "className")
    private String className;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monitorId")
    private Student monitor;

    @OneToMany(mappedBy = "clazzId", cascade = CascadeType.ALL)
    private List<Student> listStudent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Student getMonitor() {
        return monitor;
    }

    public void setMonitor(Student monitor) {
        this.monitor = monitor;
    }

    public List<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }
}
