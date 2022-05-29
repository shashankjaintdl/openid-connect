package com.ics.certificate.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "self_signed_certificate")
public class SelfSignedCertificate {

    private Long id;
    private Date issuedDate;
    private Date expiryDate;
    private String uri; // Certificate Storage URI on cloud

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
