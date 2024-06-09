package com.fastx.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fastx.dto.AuthRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.fastx.dto.BookingDTO;
import com.fastx.exceptions.BookingNotFoundException;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.services.IBookingService;
import com.fastx.services.JwtService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
	@Autowired
	private IBookingService bookingService;
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER') OR hasAuthority('ROLE_OPERATOR')")
	@PostMapping("/add")
	public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) throws ResourceNotFoundException, BookingNotFoundException
	{
		BookingDTO booking = this.bookingService.createBooking(bookingDTO);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.CREATED);
	}
	
	//user
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PostMapping("/user/{userId}/bus/{busId}")
	public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO,
			@PathVariable Integer userId, @PathVariable Integer busId) throws ResourceNotFoundException
	{
		BookingDTO booking = this.bookingService.createBooking(bookingDTO, userId, busId);
		return new ResponseEntity<BookingDTO>(booking,HttpStatus.CREATED);
	}
	
	//update
	//user, operator
	@PreAuthorize("hasAuthority('ROLE_USER') OR hasAuthority('ROLE_OPERATOR')" )
	@PutMapping("/update/{BookingId}")
	public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO BookingDTO,@PathVariable Integer BookingId) throws ResourceNotFoundException, BookingNotFoundException{
		BookingDTO updatedBooking = this.bookingService.updateBooking(BookingDTO,BookingId);
		return new ResponseEntity<BookingDTO>(updatedBooking,HttpStatus.OK);
	}
	
	//delete
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER') OR hasAuthority('ROLE_OPERATOR')")
	//all
	@DeleteMapping("/delete/{BookingId}")
	public ResponseEntity<?> deleteBooking(@PathVariable Integer BookingId) throws ResourceNotFoundException, BookingNotFoundException{
		this.bookingService.deleteBooking(BookingId);
		//return new ResponseEntity<ApiResponse>(new ApiResponse("Booking deleted successfully!!!",true),HttpStatus.OK);
		//return ResponseEntity.ok().body("Booking ID: " + BookingId + " deleted");
		return new ResponseEntity(Map.of("message","Booking Deleted Successfully"),HttpStatus.OK);
	}

	//get
	@PreAuthorize("hasAuthority('ROLE_OPERATOR')")
	//operator
	@GetMapping("/get/{BookingId}")
	public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer BookingId) throws ResourceNotFoundException, BookingNotFoundException{
		BookingDTO BookingDTO = this.bookingService.getBookingById(BookingId);
		return new ResponseEntity<BookingDTO>(BookingDTO,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_OPERATOR')")
	//getall
	//operator
	@GetMapping("/getAll")
	public ResponseEntity<List<BookingDTO>> getAllBookings() throws ResourceNotFoundException{
		List<BookingDTO> Bookings = this.bookingService.getAllBookings();
		return ResponseEntity.ok(Bookings);
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/getbyuser/{userid}")
	public ResponseEntity<List<BookingDTO>> getAllBookings(@PathVariable Integer userid) throws ResourceNotFoundException{
		List<BookingDTO> Bookings = this.bookingService.findByUserId(userid);
		return ResponseEntity.ok(Bookings);
	}
	
	@PreAuthorize("hasAuthority('ROLE_OPERATOR')")
	//admin
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to forbidden method";
	}
}
