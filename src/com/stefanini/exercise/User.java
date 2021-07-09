package com.stefanini.exercise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 4090566741189984671L;
    private String firstName;
    private String lastName;
    private String userName;
    private List<Task> tasks = null;


    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public User() {
    }

    public void addTask(Task task) {
        if (tasks == null){
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Task> getTasks() {
        return tasks;
    }



    @Override
    public String toString() {
        return "\nUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", tasks=" + tasks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User us = (User) o;
            return (lastName.equals(us.lastName) && firstName.equals(us.firstName) && userName.equals(us.userName));
        } else {
            return false;
        }
    }
}
