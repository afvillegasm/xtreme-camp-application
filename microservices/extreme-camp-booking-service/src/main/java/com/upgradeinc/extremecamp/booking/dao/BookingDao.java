package com.upgradeinc.extremecamp.booking.dao;

import java.util.Date;
import java.util.List;

import com.upgradeinc.extremecamp.booking.entity.Booking;

public interface BookingDao {
	
	public void create(Booking entity) throws Exception;
	public void update(Booking entity) throws Exception;
	public void delete(Booking entity) throws Exception;
	public List<Booking> findAll() throws Exception;
	public Booking findById(Long id) throws Exception;
	public Booking findByBookingCode(String bookingCode) throws Exception;
	public List<Booking> findByIdCampSiteIdCustomerBookingInitDateBookingEndDate(Long idCampSite, Long idCustomer, Date bookingInitDate, Date bookingEndDate) throws Exception;
	public boolean validateBookingAvailabilityForDateRange(Long idCampSite, Integer maxNumReservationsPerDay, Date bookingInitDate, Date bookingEndDate) throws Exception;

}
