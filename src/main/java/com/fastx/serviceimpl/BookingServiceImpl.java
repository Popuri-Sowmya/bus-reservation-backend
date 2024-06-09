package com.fastx.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fastx.exceptions.*;
import com.fastx.dto.BookingDTO;
import com.fastx.dto.BusDTO;
import com.fastx.dto.RouteDTO;
import com.fastx.dto.UserDTO;
import com.fastx.models.Booking;
import com.fastx.models.Bus;
import com.fastx.models.BusOperator;
import com.fastx.models.Route;
import com.fastx.models.User;
import com.fastx.repositories.BookingRepository;
import com.fastx.repositories.BusOperatorRepository;
import com.fastx.repositories.BusRepository;
import com.fastx.repositories.RouteRepository;
import com.fastx.repositories.UserRepository;
import com.fastx.services.IBookingService;
import com.fastx.services.IBusOperatorService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements IBookingService{
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BusRepository busRepo;
	
	@Autowired
	private RouteRepository routeRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public BookingDTO createBooking(BookingDTO bookingDTO) throws BookingNotFoundException {
	    Booking booking = this.modelMapper.map(bookingDTO, Booking.class);

	    // Save or merge the User entity
	    User user = booking.getUser();
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user = entityManager.merge(user);
	    user.getBookingList().add(booking);
	    UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
	    // Save the Bus entity and its associated Route
	    Bus bus = booking.getBus();
	    if (bus != null) {
	        Route route = bus.getRoute();
	        route = this.routeRepo.save(route);
	        bus.setRoute(route);

	        bus = this.busRepo.save(bus);
	        bus.getBookingList().add(booking);
	    }

	    // Save the Booking entity
	    booking = this.bookingRepo.save(booking);
	    BookingDTO savedBookingDTO = this.modelMapper.map(booking, BookingDTO.class);
	    savedBookingDTO.setUserDTO(userDTO);
	    savedBookingDTO.setBusDTO(bookingDTO.getBusDTO());
	    // Map the saved Booking entity to a DTO and return it
	    return savedBookingDTO;
	}
	
	@Override
	public BookingDTO createBooking(BookingDTO bookingDTO, Integer userId, Integer busId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ","user id",userId));
		Bus bus=this.busRepo.findById(busId).orElseThrow(()->new ResourceNotFoundException("Bus","bus id",busId));
		BusDTO busDTO = this.modelMapper.map(bus, BusDTO.class);
		//BusOperator operator = this.operatorRepo.findById(operatorId).orElseThrow(()->new ResourceNotFoundException("Bus Operator","operator id",operatorId));
		Booking booking = this.modelMapper.map(bookingDTO, Booking.class);
		user.setRoles("ROLE_USER");
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		booking.setUser(user);
		booking.setBus(bus);
		booking = this.bookingRepo.save(booking);
		BookingDTO savedBookingDTO = this.modelMapper.map(booking, BookingDTO.class);
	    savedBookingDTO.setUserDTO(userDTO);
	    savedBookingDTO.setBusDTO(busDTO);
	    return savedBookingDTO;
	}
	
	@Override
	public BookingDTO updateBooking(BookingDTO bookingDTO,Integer bookingid) throws BookingNotFoundException {
		
				Booking booking=this.bookingRepo.findById(bookingid).orElseThrow(()->new BookingNotFoundException("Booking not found while updating with ID: "+bookingid));
				
				booking.setBookingStatus(bookingDTO.getBookingStatus());
				booking.setBookingDate(bookingDTO.getBookingDate());
				booking.setBookingTime(bookingDTO.getBookingTime());
				booking.setSource(bookingDTO.getSource());
				booking.setDestination(bookingDTO.getDestination());
				booking.setJourneyDate(bookingDTO.getJourneyDate());
				booking.setNoOfSeatsBooked(bookingDTO.getNoOfSeatsBooked());
				booking.setFare(bookingDTO.getFare());
				UserDTO userDTO = bookingDTO.getUserDTO();
				userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
				booking.setUser(this.userRepo.save(this.modelMapper.map(userDTO, User.class)));
				BusDTO busDTO = bookingDTO.getBusDTO();
				RouteDTO routeDTO = busDTO.getRoute();
				Route route = this.routeRepo.save(this.modelMapper.map(routeDTO, Route.class));
				Bus bus = this.modelMapper.map(busDTO, Bus.class);
				bus.setRoute(route);
				bus = this.busRepo.save(bus);
				booking.setBus(bus);
				Booking updatedBooking=this.bookingRepo.save(booking);
				BookingDTO updatedBookingDTO=this.modelMapper.map(updatedBooking, BookingDTO.class);
				updatedBookingDTO.setBusDTO(busDTO);
				updatedBookingDTO.setUserDTO(userDTO);
				return updatedBookingDTO;
	}

	@Override
	public void deleteBooking(Integer bookingId) throws BookingNotFoundException {
		
		Booking booking=this.bookingRepo.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Booking not found while deleting with ID: "+bookingId));
		this.bookingRepo.delete(booking);
	}

	@Override
	public List<BookingDTO> getAllBookings() {
		// TODO Auto-generated method stub
		List<Booking> bookings = this.bookingRepo.findAll();
		List<BookingDTO> allBookings = bookings.stream().map((booking)->this.modelMapper.map(booking,BookingDTO.class)).collect(Collectors.toList());
		for(Booking booking: bookings) {
			for(BookingDTO bookdto: allBookings) {
				if(booking.getBookingid() == bookdto.getBookingid()) {
				bookdto.setUserDTO(this.modelMapper.map(booking.getUser(), UserDTO.class));
				bookdto.setBusDTO(this.modelMapper.map(booking.getBus(), BusDTO.class));}
			}
		}
		return allBookings;
	}

	@Override
	public BookingDTO getBookingById(Integer bookingId) throws BookingNotFoundException {
		// TODO Auto-generated method stub
		Booking booking=this.bookingRepo.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Booking not found while finding with ID: "+bookingId));
		BookingDTO bookdto = this.modelMapper.map(booking,BookingDTO.class);
		bookdto.setUserDTO(this.modelMapper.map(booking.getUser(), UserDTO.class));
		bookdto.setBusDTO(this.modelMapper.map(booking.getBus(), BusDTO.class));
		return bookdto;
	}

	@Override
	public List<BookingDTO> findByUserId(Integer userId) {
		List<Booking> bookings = this.bookingRepo.findByUserId(userId);
		List<BookingDTO> allBookings = bookings.stream().map((booking)->this.modelMapper.map(booking,BookingDTO.class)).collect(Collectors.toList());
		for(Booking booking: bookings) {
			for(BookingDTO bookdto: allBookings) {
				if(booking.getBookingid() == bookdto.getBookingid()) {
				bookdto.setUserDTO(this.modelMapper.map(booking.getUser(), UserDTO.class));
				bookdto.setBusDTO(this.modelMapper.map(booking.getBus(), BusDTO.class));}
			}
		}
		return allBookings;
	}
}
