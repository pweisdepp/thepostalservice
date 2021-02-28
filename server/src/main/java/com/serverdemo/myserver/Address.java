package com.serverdemo.myserver;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String country;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}