package com.upgradeinc.extremecamp.booking.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upgradeinc.extremecamp.booking.entity.Booking;
import com.upgradeinc.extremecamp.booking.common.Constants;

@Repository
public class BookingDaoImpl implements BookingDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void create(Booking entity) throws Exception {
		
		em.persist(entity);
		
	}

	@Override
	@Transactional
	public void update(Booking entity) throws Exception {
		
		em.merge(entity);
		
	}

	@Override
	@Transactional
	public void delete(Booking entity) throws Exception {
		
		em.merge(entity);
		
	}

	@Override
	public List<Booking> findAll() throws Exception {
		
		Query q = em.createNamedQuery("Booking.findAll", Booking.class);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		
		return q.getResultList();
		
	}

	@Override
	public Booking findById(Long id) throws Exception {
		
		Query q = em.createNamedQuery("Booking.findById", Booking.class);
		q.setParameter("id", id);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		List<Booking> lst = (List<Booking>)q.getResultList();
		
		if(lst != null && lst.size() > 0) {
			return lst.get(0);
		}
		
		return null;
		
	}

	@Override
	public Booking findByBookingCode(String bookingCode) throws Exception {
		
		Query q = em.createNamedQuery("Booking.findByBookingCode", Booking.class);
		q.setParameter("bookingCode", bookingCode);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		List<Booking> lst = (List<Booking>)q.getResultList();
		
		if(lst != null && lst.size() > 0) {
			return lst.get(0);
		}
		
		return null;
		
	}

	@Override
	public boolean validateBookingAvailabilityForDateRange(Long idCampSite, Integer maxNumReservationsPerDay, Date bookingInitDate, Date bookingEndDate) throws Exception {
		
		SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar cinit = Calendar.getInstance();
		cinit.set(Calendar.HOUR_OF_DAY, 0);
		cinit.set(Calendar.MINUTE, 0);
		cinit.set(Calendar.SECOND, 0);
		cinit.set(Calendar.MILLISECOND, 0);
				
		String[] formDate = isoFormat.format(bookingInitDate).split("-");
		
		cinit.set(Calendar.DAY_OF_MONTH,Integer.parseInt(formDate[0]));
		cinit.set(Calendar.MONTH,Integer.parseInt(formDate[1]) - 1);
		cinit.set(Calendar.YEAR,Integer.parseInt(formDate[2]));
		
		Calendar cend = Calendar.getInstance();
		cend.set(Calendar.HOUR_OF_DAY, 0);
		cend.set(Calendar.MINUTE, 0);
		cend.set(Calendar.SECOND, 0);
		cend.set(Calendar.MILLISECOND, 0);
				
		formDate = isoFormat.format(bookingEndDate).split("-");
		
		cend.set(Calendar.DAY_OF_MONTH,Integer.parseInt(formDate[0]));
		cend.set(Calendar.MONTH,Integer.parseInt(formDate[1]) - 1);
		cend.set(Calendar.YEAR,Integer.parseInt(formDate[2]));
		
		Query q = em.createQuery("select o from Booking o where o.idCampSite = :idCampSite and o.status <> :status and ( (o.bookingInitDate >= :bookingInitDate and o.bookingInitDate <= :bookingEndDate) or (o.bookingEndDate >= :bookingInitDate and o.bookingEndDate <= :bookingEndDate) or (o.bookingInitDate <= :bookingInitDate and o.bookingEndDate >= :bookingEndDate) or (o.bookingInitDate <= :bookingInitDate and o.bookingEndDate >= :bookingInitDate) or (o.bookingInitDate <= :bookingEndDate and o.bookingEndDate >= :bookingEndDate) )", Booking.class);
		q.setParameter("idCampSite", idCampSite);
		q.setParameter("bookingInitDate", cinit.getTime());
		q.setParameter("bookingEndDate", cend.getTime());
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		
		List<Booking> lst = (List<Booking>)q.getResultList();
		
		/*validating bookings*/
		Long sumOpenedRanges = lst.stream().filter(x -> x.getBookingInitDate().compareTo(cinit.getTime()) >= 0 && x.getBookingInitDate().compareTo(cend.getTime()) <= 0)
                .count();
			
		Long sumClosedRanges = lst.stream().filter(x -> x.getBookingEndDate().compareTo(cinit.getTime()) >= 0 && x.getBookingEndDate().compareTo(cend.getTime()) <= 0)
                .count();
		
		Long sumOpenCloseRanges = lst.stream().filter(x -> x.getBookingInitDate().compareTo(cinit.getTime()) <= 0 && x.getBookingEndDate().compareTo(cend.getTime()) >= 0)
                .count();
		
		long sumOnlyIncludeInitRange = lst.stream().filter(x -> x.getBookingInitDate().compareTo(cinit.getTime()) <= 0 && x.getBookingEndDate().compareTo(cinit.getTime()) >= 0)
                .count();
		
		long sumOnlyIncludeEndRange = lst.stream().filter(x -> x.getBookingInitDate().compareTo(cend.getTime()) <= 0 && x.getBookingEndDate().compareTo(cend.getTime()) >= 0)
                .count();
		
		boolean availableDateRange = ( (sumOpenedRanges > maxNumReservationsPerDay) || 
									 (sumClosedRanges >= maxNumReservationsPerDay) || 
									 (sumOpenCloseRanges >= maxNumReservationsPerDay) || 
									 (sumOnlyIncludeInitRange >= maxNumReservationsPerDay) || 
									 (sumOnlyIncludeEndRange >= maxNumReservationsPerDay) ) ? false : true;
		
		return availableDateRange;
		
	}

	@Override
	public List<Booking> findByIdCampSiteIdCustomerBookingInitDateBookingEndDate(Long idCampSite, Long idCustomer,
			Date bookingInitDate, Date bookingEndDate) throws Exception {
		
		Query q = em.createNamedQuery("Booking.findByIdCampSiteIdCustomerBookingInitDateBookingEndDate", Booking.class);
		q.setParameter("idCampSite", idCampSite);
		q.setParameter("idCustomer", idCustomer);
		q.setParameter("bookingInitDate", bookingInitDate);
		q.setParameter("bookingEndDate", bookingEndDate);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		
		return q.getResultList();
		
	}

}
