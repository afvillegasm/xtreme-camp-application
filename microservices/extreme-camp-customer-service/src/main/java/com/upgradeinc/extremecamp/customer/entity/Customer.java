package com.upgradeinc.extremecamp.customer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CUSTOMERS")
@NamedQueries(value = {
		@NamedQuery(name = "Customer.findAll",query = "select o from Customer o where o.status <> :status"),
		@NamedQuery(name = "Customer.findById",query = "select o from Customer o where o.id = :id and o.status <> :status"),
		@NamedQuery(name = "Customer.findByEmail",query = "select o from Customer o where o.email = :email")
})
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;
	@Column(name = "LASTNAME", nullable = false)
	private String lastName;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name = "STATUS", nullable = false)
	private String status;
	@Column(name = "CREATEDBY", nullable = false)
	private String createdBy;
	@Column(name = "CREATEDAT", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	@Column(name = "MODIFIEDBY", nullable = false)
	private String modifiedBy;
	@Column(name = "MODIFIEDAT", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date modifiedAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
