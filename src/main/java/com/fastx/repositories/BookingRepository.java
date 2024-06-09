package com.fastx.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fastx.models.Booking;
import com.fastx.models.Bus;
import com.fastx.models.BusOperator;
import com.fastx.models.Route;
import com.fastx.models.User;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	@Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> findByUserId(Integer userId);
//	
//	@Query("SELECT b FROM Booking b WHERE b.operator.id = :operatorId")
//    List<Booking> findByOperatorId(@Param("operatorId") int operatorId);
//
//    @Query("SELECT b FROM Booking b WHERE b.route.id = :routeId")
//    List<Booking> findByRouteId(@Param("routeId") int routeId);
//
//    @Query("SELECT b FROM Booking b WHERE b.bus.id = :busId")
//    List<Booking> findByBusId(@Param("busId") int busId);
//
//    @Query("SELECT b FROM Booking b WHERE b.bookingStatus = :bookingStatus")
//    List<Booking> findByBookingStatus(@Param("bookingStatus") String bookingStatus);
}
