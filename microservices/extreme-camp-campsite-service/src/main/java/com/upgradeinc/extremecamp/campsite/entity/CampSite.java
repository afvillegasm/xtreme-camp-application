package com.upgradeinc.extremecamp.campsite.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CAMPSITES")
@NamedQueries(value = {
		@NamedQuery(name = "CampSite.findAll",query = "select o from CampSite o where o.status <> :status"),
		@NamedQuery(name = "CampSite.findById",query = "select o from CampSite o where o.id = :id and o.status <> :status"),
		@NamedQuery(name = "CampSite.findByName",query = "select o from CampSite o where o.name = :name")
})
public class CampSite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	@Column(name = "FOUNDATIONDATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date foundationDate;
	@Column(name = "MAXNUMRESERVATIONSPERDAY", nullable = false)
	private Integer maxNumReservationsPerDay;
	@Column(name = "MAXNUMDAYSINCLUDEDINDATERANGE", nullable = false)
	private Integer maxNumDaysIncludedInDateRange;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getFoundationDate() {
		return foundationDate;
	}
	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}
	public Integer getMaxNumReservationsPerDay() {
		return maxNumReservationsPerDay;
	}
	public void setMaxNumReservationsPerDay(Integer maxNumReservationsPerDay) {
		this.maxNumReservationsPerDay = maxNumReservationsPerDay;
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
	public Integer getMaxNumDaysIncludedInDateRange() {
		return maxNumDaysIncludedInDateRange;
	}
	public void setMaxNumDaysIncludedInDateRange(Integer maxNumDaysIncludedInDateRange) {
		this.maxNumDaysIncludedInDateRange = maxNumDaysIncludedInDateRange;
	}
}
