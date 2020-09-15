package com.adam.model;

import javax.persistence.*;

@Entity
@Table(name = "clazz")
@NamedQuery(query = "SELECT c FROM Clazz c", name = "getAllClazz")
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "className")
    private String className;

    @Column(name = "monitorId")
    private int monitorId;

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

    public int getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(int monitorId) {
        this.monitorId = monitorId;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", monitorId=" + monitorId +
                '}';
    }

}
