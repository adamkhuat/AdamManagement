package com.adam.model;

import javax.persistence.*;

@Entity
@Table(name = "studentClass")
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int clazzId;

    @Column
    private int studentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "id=" + id +
                ", clazzId=" + clazzId +
                ", studentId=" + studentId +
                '}';
    }
}
