package com.ajulay.entity;

import com.ajulay.enumirated.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(unique = true)
    private String login;

    private String passwordHash;

    private String name;

    private String surname;

    private String lastName;

    private Role role = Role.WORKER;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

}
