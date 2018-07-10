package com.example.demo.models;

import com.example.demo.models.AppRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fullName;
    private String userName;
    private String passWord;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    Set<AppRole> roles;

    public AppUser(){
        this.roles = new HashSet<>();
    }

    public AppUser(String userName, String passWord) {
        this.roles = new HashSet<>();
        this.userName = userName;
        this.passWord = passWord;
    }

    public void addRole(AppRole role){
        roles.add(role);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = new BCryptPasswordEncoder().encode(passWord);
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }
}

