package com.dz.app.model.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class BaseProperties {

	@Column(name = "ACTIVE")
	private String active;
	
	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "CREATEDON")
	private Date createdOn;
	
	@Column(name = "UPDATEDON")
	private Date updatedOn;
	
	@Column(name = "UPDATEDBY")
	private String updatedBy;

	
	public BaseProperties(String active,Date createdOn, String createdBy, Date updatedOn, String updatedBy) {
		super();
		this.active = active;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "BaseProperties [active=" + active + ", createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy + "]";
	}
	public BaseProperties() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
