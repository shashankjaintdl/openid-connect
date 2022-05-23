package com.ics.openid.connect.models;

import javax.persistence.*;

@Entity
@Table(name = "blacklisted_site")
public class BlacklistedSite {

    private Long id;

    private String uri;


    public BlacklistedSite(){}

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
