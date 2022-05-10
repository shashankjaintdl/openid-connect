//package com.ics.auth.server.models;
//
//import com.ics.common.Gender;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "user_profile")
//public class UserProfile {
//
//    private Long id;
//
//    private String firstName;
//    private String lastName;
//    private String emailId;
//    private boolean isEmailVerified;
//    private String phoneNo;
//    private String isPhoneNoVerified;
//    private Gender gender;
//    private Date dob;
//    private User user;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Column(name = "first_name", columnDefinition = "TEXT",length = 40)
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    @Column(name = "last_name",columnDefinition = "TEXT",length = 40)
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Column(name = "email_id",unique = true)
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "gender")
//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "dob")
//    public Date getDob() {
//        return dob;
//    }
//
//    public void setDob(Date dob) {
//        this.dob = dob;
//    }
//
//    @OneToOne
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
