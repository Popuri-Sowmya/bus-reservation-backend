package com.fastx.services;

import java.util.List;

import com.fastx.dto.BookingDTO;
import com.fastx.exceptions.BookingNotFoundException;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.models.Booking;

public interface IBookingService {
	
	//create
	BookingDTO createBooking(BookingDTO bookingDTO) throws ResourceNotFoundException, BookingNotFoundException;
	//BookingDTO createBooking(BookingDTO bookingDTO, Integer userId, Integer operatorId, Integer busId) throws ResourceNotFoundException;
	//update
	BookingDTO updateBooking(BookingDTO bookingDTO, Integer bookingId) throws ResourceNotFoundException, BookingNotFoundException;
	
	//delete
	void deleteBooking(Integer bookingId) throws ResourceNotFoundException, BookingNotFoundException;
	
	//get all
	List<BookingDTO> getAllBookings();
	
	//get by id
	BookingDTO getBookingById(Integer bookingId) throws ResourceNotFoundException, BookingNotFoundException;
	
	BookingDTO createBooking(BookingDTO bookingDTO, Integer userId, Integer busId) throws ResourceNotFoundException;
	
	List<BookingDTO> findByUserId(Integer userId);
}
