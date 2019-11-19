package com.hongda.domain;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "grandparent")
public class Grandparent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "last_vaccine_date")
    private Date lastVaccineDate;

    @Column(name = "next_vaccine_date")
    private Date nextVaccineDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User userid;

    public Grandparent() {
    }

    public Grandparent(String name, String email, String relationship, Date lastVaccineDate, Date nextVaccineDate, User userid, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.relationship = relationship;
        this.lastVaccineDate = lastVaccineDate;
        this.nextVaccineDate = nextVaccineDate;
        this.userid = userid;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Date getLastVaccineDate() {
        return lastVaccineDate;
    }

    public void setLastVaccineDate(Date lastVaccineDate) {
        this.lastVaccineDate = lastVaccineDate;
    }

    public Date getNextVaccineDate() {
        return nextVaccineDate;
    }

    public void setNextVaccineDate(Date nextVaccineDate) {
        this.nextVaccineDate = nextVaccineDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Grandparent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", relationship='" + relationship + '\'' +
                ", lastVaccineDate=" + lastVaccineDate +
                ", nextVaccineDate=" + nextVaccineDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userid=" + userid +
                '}';
    }
}
