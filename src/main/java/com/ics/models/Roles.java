package com.ics.models;


import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {

    private Long id;
    private String role;
    private String desc;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role", unique = true)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "description")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
