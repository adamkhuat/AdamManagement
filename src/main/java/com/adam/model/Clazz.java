package com.adam.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clazz")
@NamedQuery(query = "SELECT c FROM Clazz c", name = "getAllClazz")
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "className")
    private String className;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "monitorId")
    private Student monitor;

    @OneToMany(fetch = FetchType.LAZY,
            targetEntity = StudentClass.class,
            mappedBy = "clazz",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<StudentClass> listStudent;

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

    public List<StudentClass> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<StudentClass> listStudent) {
        this.listStudent = listStudent;
    }
}
