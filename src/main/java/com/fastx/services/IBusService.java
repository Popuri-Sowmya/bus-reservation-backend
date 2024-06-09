package com.fastx.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.fastx.dto.BusDTO;
import com.fastx.exceptions.BusNotFoundException;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.models.Bus;

public interface IBusService {
		//create
		BusDTO createBus(BusDTO busDTO);
		//update
		BusDTO updateBus(BusDTO busDTO, Integer busId) throws ResourceNotFoundException, BusNotFoundException;
		//delete
		void deleteBus(Integer busId) throws ResourceNotFoundException, BusNotFoundException;
		//get
		BusDTO getBus(Integer busId) throws ResourceNotFoundException, BusNotFoundException;
		//get all
		List<BusDTO> getBuses();
		
		List<BusDTO> searchBus(String routeFrom,String routeTo,String busJourneyDate);
		public List<BusDTO> findByPriceRange(int minPrice, int maxPrice);
		List<BusDTO> findByDepartureTime(String departureTime);
		List<BusDTO> findByBusType(String busType);
}
