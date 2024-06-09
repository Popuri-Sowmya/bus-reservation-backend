package com.fastx.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastx.dto.BusDTO;
import com.fastx.dto.BusOperatorDTO;
import com.fastx.models.Bus;
import com.fastx.models.BusOperator;
import com.fastx.models.Route;
import com.fastx.repositories.BusRepository;
import com.fastx.repositories.RouteRepository;
import com.fastx.services.IBusService;
import com.fastx.exceptions.BusNotFoundException;

@Service
public class BusServiceImpl implements IBusService{
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private RouteRepository routeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BusDTO createBus(BusDTO busDTO) {
		Bus bus = this.modelMapper.map(busDTO, Bus.class);
		Route route = bus.getRoute();
		if (!route.getRouteFrom().equals(busDTO.getRouteFrom()) || 
	            !route.getRouteTo().equals(busDTO.getRouteTo())) {
	            throw new IllegalArgumentException("The route of the bus does not match the specified route.");
	    }
		Route savedRoute = this.routeRepo.save(route);
		bus.setRoute(savedRoute);
		savedRoute.getBusList().add(bus);
		Bus addedBus = this.busRepository.save(bus);
		return this.modelMapper.map(addedBus,BusDTO.class);
	}

	@Override
	public BusDTO updateBus(BusDTO busDTO,Integer busId) throws BusNotFoundException {
		Optional<Bus> busop = this.busRepository.findById(busId);
		Bus bus = busop.get();
		if(busop.isEmpty()) {
			throw new BusNotFoundException("Bus not found with id: "+busId+" while updating");
		}
		bus.setBusName(busDTO.getBusName());
		bus.setDriverName(busDTO.getDriverName());
		bus.setBusType(busDTO.getBusType());
		bus.setBusRegNumber(busDTO.getBusRegNumber());
		bus.setRouteFrom(busDTO.getRouteFrom());
		bus.setRouteTo(busDTO.getRouteTo());
		bus.setArrivalTime(busDTO.getArrivalTime());
		bus.setDepartureTime(busDTO.getDepartureTime());
		bus.setTotalSeats(busDTO.getTotalSeats());
		bus.setAvailableSeats(busDTO.getAvailableSeats());
		bus.setFarePerSeat(busDTO.getFarePerSeat());
		bus.setBusJourneyDate(busDTO.getBusJourneyDate());
		bus.setTrackingURL(busDTO.getTrackingURL());
		Bus addedBus = this.busRepository.save(bus);
		return this.modelMapper.map(addedBus,BusDTO.class);
	}



	@Override
	public void deleteBus(Integer busid) throws BusNotFoundException {
		Optional<Bus> busop = this.busRepository.findById(busid);
		Bus bus = busop.get();
		if(busop.isEmpty()) {
			throw new BusNotFoundException("Bus not found with id: "+busid+" while deleting");
		}
		this.busRepository.delete(bus);
	}

	@Override
	public BusDTO getBus(Integer busid) throws BusNotFoundException {
		Optional<Bus> busop = this.busRepository.findById(busid);
		Bus bus = busop.get();
		if(busop.isEmpty()) {
			throw new BusNotFoundException("Bus not found with id: "+busid+" while retrieving");
		}
		return this.modelMapper.map(bus,BusDTO.class);
	}

	@Override
	public List<BusDTO> getBuses() {
		List<Bus> buses = this.busRepository.findAll();
		List<BusDTO> allBuses = buses.stream().map((bus)->this.modelMapper.map(bus,BusDTO.class)).collect(Collectors.toList());
		return allBuses;
	}
//	@Override
//	public List<BusDTO> getSearchBus() {
//        List<Bus> buses = this.busRepository.findAll();
//        List<BusDTO> allBuses = buses.stream().map(bus ->this.modelMapper.map(bus, BusDTO.class)).collect(Collectors.toList());
//        return allBuses;
//    }
//

	@Override
	public List<BusDTO> searchBus(String routeFrom,String routeTo,String busJourneyDate) {
		
		// TODO Auto-generated method stub
		LocalDate parsedDate = LocalDate.parse(busJourneyDate);
        List<Bus> buses = busRepository.findByRouteFromRouteToAndBusJourneyDate(routeFrom, routeTo, parsedDate);

        // Call the repository method to execute the JPQL query
		List<BusDTO> allBuses = buses.stream().map((bus)->this.modelMapper.map(bus,BusDTO.class)).collect(Collectors.toList());


		return allBuses;
	}

	@Override
	public List<BusDTO> findByPriceRange(int minPrice, int maxPrice) {
		
		// TODO Auto-generated method stub
		 List<Bus> buses = busRepository.findByPriceRange(minPrice,maxPrice);

	        // Call the repository method to execute the JPQL query
			List<BusDTO> allBuses = buses.stream().map((bus)->this.modelMapper.map(bus,BusDTO.class)).collect(Collectors.toList());


			return allBuses;
		
	}

	@Override
	public List<BusDTO> findByDepartureTime(String departureTime) {
		// TODO Auto-generated method stub
		LocalTime parsedTime = LocalTime.parse(departureTime);
		List<Bus> buses = busRepository.findByDepartureTime(parsedTime);

        // Call the repository method to execute the JPQL query
		List<BusDTO> allBuses = buses.stream().map((bus)->this.modelMapper.map(bus,BusDTO.class)).collect(Collectors.toList());


		return allBuses;
		
	}

	@Override
	public List<BusDTO> findByBusType(String busType) {
		// TODO Auto-generated method stub
		 List<Bus> buses = busRepository.findByBusType(busType);

	        // Call the repository method to execute the JPQL query
			List<BusDTO> allBuses = buses.stream().map((bus)->this.modelMapper.map(bus,BusDTO.class)).collect(Collectors.toList());

			return allBuses;
		
	}
}
