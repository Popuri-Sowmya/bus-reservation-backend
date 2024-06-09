package com.fastx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fastx.dto.BusDTO;
import com.fastx.models.Bus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
	
//    List<Bus> findByRouteFromAndRouteTo(String routeFrom, String routeTo);
//
//    List<Bus> findByBusJourneyDate(LocalDate journeyDate);
//
//    List<Bus> findByRouteFromAndBusJourneyDate(String routeFrom, LocalDate journeyDate);
//
//    List<Bus> findByRouteToAndBusJourneyDate(String routeTo, LocalDate journeyDate);
	@Query("SELECT b FROM Bus b WHERE b.routeFrom = :routeFrom AND b.routeTo = :routeTo AND b.busJourneyDate = :busJourneyDate")
	List<Bus> findByRouteFromRouteToAndBusJourneyDate(String routeFrom,String routeTo,LocalDate busJourneyDate);

	@Query("SELECT b FROM Bus b WHERE b.farePerSeat >= :minPrice AND b.farePerSeat <= :maxPrice")
    List<Bus> findByPriceRange(int minPrice, int maxPrice);
	
	@Query("SELECT b FROM Bus b WHERE b.departureTime = :departureTime")
    List<Bus> findByDepartureTime(LocalTime departureTime);

	@Query("SELECT b FROM Bus b WHERE b.busType = :busType")
    List<Bus> findByBusType(String busType);
	
	
    //@Query("SELECT b FROM Bus b WHERE b.id = :id")
    public BusDTO findById(int busid);
}
