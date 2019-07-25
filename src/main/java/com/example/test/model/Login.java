package com.example.test.model;

import org.hibernate.annotations.Type;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "test")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login_time")
    @Temporal(TemporalType.DATE)
    private Date login_time;

    @Column(name = "user")
    private String user;

    @Column(name = "attribute1")
    private String attribute1;

    @Column(name = "attribute2")
    private String attribute2;

    @Column(name = "attribute3")
    private String attribute3;

    @Column(name = "attribute4")
    private String attribute4;

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }


}