package com.dz.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {

	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="STREET")
    private String street;
    
    @Column(name="CITY")
    private String city;
    
    
    @Column(name="STATE")
    private String state;
    
    @Column(name="PINCODE")
    private String pinCode;
    
    @Column(name="COUNTRY")
    private String country;

    
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String street, String city, String state, String pinCode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.country = country;
	}

	public Address(String street, String city) {
		super();
		this.street = street;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", pinCode="
				+ pinCode + ", country=" + country + "]";
	}
    
    
}
