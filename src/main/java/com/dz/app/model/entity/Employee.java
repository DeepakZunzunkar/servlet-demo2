package com.dz.app.model.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;



/**
 * @author dz Mar 17, 2023
 *
 */


@NamedNativeQueries({
	@NamedNativeQuery(name="sql_findAll",query="select eid,firstname,lastname,gender,birthDate,salary,status,active,createdon,createdby,updatedon,updatedby from AdpEmployee")
})

@NamedQueries({
	
	
	@NamedQuery(name="hql_findAll",query="from Employee order by firstName asc"),
	@NamedQuery(name="hql_findById",query="from Employee where eid=:eid"),
	@NamedQuery(name="hql_updateAll" ,query="update Employee set firstname=:fname,lastname=:lname,gender=:gender,birthDate=:birthDate,salary=:salary,status=:status,active=:active,updatedby=:updatedby,updatedon=:updatedon where eid=:eid"),
	@NamedQuery(name="hql_deleteById", query="delete Employee where eid=:eid"),
	
})
@Entity
@Table(name="AdpEmployee",schema="EMPRDEV")
public class Employee {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long eid;
    
    @Column(name="FIRSTNAME")
    private String firstName;
    
    @Column(name="LASTNAME")
    private String lastName;
    
//    @Enumerated(EnumType.STRING)
    @Column(name="GENDER")
    private String gender;

//    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private String status;

    @Embedded
    	@AttributeOverrides({
    		@AttributeOverride(name="createdOn",column=@Column(name="CREATEDON",updatable= false))
    	})
    private BaseProperties baseProperties;
    
    @Temporal(TemporalType.DATE)
    private Date birthDate;
//    
//    @Transient
//    private Integer age;

    @Column(name="SALARY")
    private Double salary;
    
	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BaseProperties getBaseProperties() {
		return baseProperties;
	}

	public void setBaseProperties(BaseProperties baseProperties) {
		this.baseProperties = baseProperties;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
//	public Integer getAge() {
//		return age;
//	}
//
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//
//	@Override
//	public String toString() {
//		return "Employee [eid=" + eid + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
//				+ ", status=" + status + ", baseProperties=" + baseProperties + ", birthDate=" + birthDate + ", age="
//				+ age + "]";
//	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", status=" + status + ", baseProperties=" + baseProperties + "]";
	}
	

	public Employee() {
		super();
	}

	public Employee(String firstName, String gender) {
		super();
		this.firstName = firstName;
		this.gender = gender;
	}

	public Employee(BaseProperties baseProperties) {
		super();
		this.baseProperties = baseProperties;
	}
    
	
}
