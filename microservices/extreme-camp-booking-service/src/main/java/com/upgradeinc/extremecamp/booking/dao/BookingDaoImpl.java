package com.upgradeinc.extremecamp.booking.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

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
		
		Query q = em.createNamedQuery("Booking.findById", Booking.class);
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
		
		
		
		Query q = em.createNativeQuery(" SELECT COUNT(*) FROM "
				+ " ( "
				+ " SELECT COUNT(*) "
				+ " FROM BOOKINGS o "
				+ " WHERE o.IDCAMPSITE = :idCampSite AND o.BOOKINGINITDATE >= :initDate AND o.BOOKINGENDDATE <= :endDate AND o.STATUS <> :status "
				+ " GROUP BY o.STATUS "
				+ " HAVING COUNT(*) > :maxBookingsPerDay "
				+ " UNION "
				+ " SELECT COUNT(*) "
				+ " FROM BOOKINGS o "
				+ " WHERE o.IDCAMPSITE = :idCampSite AND o.BOOKINGENDDATE >= :initDate AND o.BOOKINGENDDATE <= :endDate AND o.STATUS <> :status "
				+ " GROUP BY o.STATUS "
				+ " HAVING COUNT(*) >= :maxBookingsPerDay "
				+ " UNION "
				+ " SELECT COUNT(*) "
				+ " FROM BOOKINGS o "
				+ " WHERE o.IDCAMPSITE = :idCampSite AND o.BOOKINGINITDATE <= :initDate AND o.BOOKINGENDDATE >= :endDate AND o.STATUS <> :status "
				+ " GROUP BY o.STATUS "
				+ " HAVING COUNT(*) >= :maxBookingsPerDay ) ");
		q.setParameter("idCampSite", idCampSite);
		q.setParameter("maxBookingsPerDay", maxNumReservationsPerDay.longValue());
		q.setParameter("initDate", bookingInitDate);
		q.setParameter("endDate", bookingEndDate);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		
		List<Object[]> lst = (List<Object[]>)q.getResultList();
		
		if(lst != null && lst.size() > 0) {
			Object result = lst.get(0);
			if(((BigInteger)result).compareTo(BigInteger.ZERO) > 0) {
				return false;
			} else {
				return true;
			}
		}
		
		return true;
		
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
