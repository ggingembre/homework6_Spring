package com.app.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Guillaume Gingembre on 30/10/2017.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username", updatable=false, nullable=false)
    private String username;

    @Column(name = "password")
    private String password;

    //@Column(name = "user_role")
    //private Role role;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles")
    @Column(name = "role") // Column name in user_roles
    private Collection<Role> roles;

    /*
    public User() {}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    } */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
            return roles;
        }

    public void setRoles(Collection<Role> roles) {
            this.roles = roles;
        }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + roles +
                '}';
    }
}
