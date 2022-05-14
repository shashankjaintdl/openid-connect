package com.ics.openid.connect.models;

import javax.persistence.*;

@Entity
@Table
public class ApprovedSite {

    private Long id;
    private String uri;

    public ApprovedSite(){
    }

    public ApprovedSite(String uri){
        this.uri = uri;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
