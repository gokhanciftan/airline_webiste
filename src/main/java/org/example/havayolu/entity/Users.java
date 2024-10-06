package org.example.havayolu.entity;

import jakarta.persistence.*;
import org.example.havayolu.entity.enums.Roles;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastName;
    private String tc;
    private String number;
    private Date registerDate;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Bilet> bilets = new ArrayList<>();

    public Users(int id, String name, String lastName, String tc, String number, Date registerDate, Roles roles, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.tc = tc;
        this.number = number;
        this.registerDate = registerDate;
        this.roles = roles;
        this.password = password;
    }

    public Users(String name, String lastName, String tc, String number, String password) {
        this.name = name;
        this.lastName = lastName;
        this.tc = tc;
        this.number = number;
        this.registerDate = new Date(System.currentTimeMillis());
        this.roles = Roles.NORMAL;
        this.password = password;
    }

    public Users() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Bilet> getBilets() {
        return bilets;
    }

    public void setBilets(List<Bilet> bilets) {
        this.bilets = bilets;
    }
}
