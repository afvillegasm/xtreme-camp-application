package com.upgradeinc.extremecamp.booking.entity;

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
@Table(name = "BOOKINGS")
@NamedQueries(value = {
		@NamedQuery(name = "Booking.findAll",query = "select o from Booking o where o.status <> :status"),
		@NamedQuery(name = "Booking.findById",query = "select o from Booking o where o.id = :id and o.status <> :status"),
		@NamedQuery(name = "Booking.findByBookingCode",query = "select o from Booking o where o.bookingCode = :bookingCode and o.status <> :status"),
		@NamedQuery(name = "Booking.findByIdCampSiteIdCustomerBookingInitDateBookingEndDate",query = "select o from Booking o where o.idCampSite = :idCampSite and o.idCustomer = :idCustomer and o.bookingInitDate = :bookingInitDate and o.bookingEndDate = :bookingEndDate and o.status <> :status")
})
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	@Column(name = "IDCAMPSITE", nullable = false)
	private Long idCampSite;
	@Column(name = "IDCUSTOMER", nullable = false)
	private Long idCustomer;
	@Column(name = "BOOKINGCODE", nullable = false)
	private String bookingCode;
	@Column(name = "BOOKINGINITDATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date bookingInitDate;
	@Column(name = "BOOKINGENDDATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date bookingEndDate;
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
	public Long getIdCampSite() {
		return idCampSite;
	}
	public void setIdCampSite(Long idCampSite) {
		this.idCampSite = idCampSite;
	}
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}
	public Date getBookingInitDate() {
		return bookingInitDate;
	}
	public void setBookingInitDate(Date bookingInitDate) {
		this.bookingInitDate = bookingInitDate;
	}
	public Date getBookingEndDate() {
		return bookingEndDate;
	}
	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
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
