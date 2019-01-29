package com.ajulay.entity;

import com.ajulay.enumirated.Role;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@ApplicationScoped
@Table(name = "\"user\"")
public class User implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String login;

    private String password;

    private String name;

    private String surname;

    private String lastName;

    private Role role = Role.WORKER;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

}
