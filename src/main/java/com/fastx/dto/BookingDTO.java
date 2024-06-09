package com.fastx.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fastx.models.Bus;
import com.fastx.models.BusOperator;
import com.fastx.models.Route;
import com.fastx.models.User;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BookingDTO {
	@NotNull
	private Integer bookingid;
	@NotEmpty
	@Size(max=10, min = 4)
    private String bookingStatus;
	@FutureOrPresent
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate bookingDate;
	@JsonFormat(pattern = "HH:mm:ss")
    private LocalTime bookingTime;
    @NotEmpty
    private String source;
    @NotEmpty
    private String destination;
    @FutureOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate journeyDate;
    @Positive
    private Integer noOfSeatsBooked;
    private double fare;
    private UserDTO userDTO;
    private BusDTO busDTO;
  
    public BookingDTO() { }

	public BookingDTO(@NotNull Integer bookingid, @NotEmpty @Size(max = 10, min = 4) String bookingStatus,
			@FutureOrPresent LocalDate bookingDate, LocalTime bookingTime, @NotEmpty String source,
			@NotEmpty String destination, @FutureOrPresent LocalDate journeyDate, @Positive Integer noOfSeatsBooked,
			double fare, UserDTO userDTO, BusDTO busDTO) {
		super();
		this.bookingid = bookingid;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.source = source;
		this.destination = destination;
		this.journeyDate = journeyDate;
		this.noOfSeatsBooked = noOfSeatsBooked;
		this.fare = fare;
		this.userDTO = userDTO;
		this.busDTO = busDTO;
	}

	public Integer getBookingid() {
		return bookingid;
	}

	public void setBookingid(Integer bookingid) {
		this.bookingid = bookingid;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public Integer getNoOfSeatsBooked() {
		return noOfSeatsBooked;
	}

	public void setNoOfSeatsBooked(Integer noOfSeatsBooked) {
		this.noOfSeatsBooked = noOfSeatsBooked;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public BusDTO getBusDTO() {
		return busDTO;
	}

	public void setBusDTO(BusDTO busDTO) {
		this.busDTO = busDTO;
	}

	@Override
	public String toString() {
		return "BookingDTO [bookingid=" + bookingid + ", bookingStatus=" + bookingStatus + ", bookingDate="
				+ bookingDate + ", bookingTime=" + bookingTime + ", source=" + source + ", destination=" + destination
				+ ", journeyDate=" + journeyDate + ", noOfSeatsBooked=" + noOfSeatsBooked + ", fare=" + fare
				+ ", userDTO=" + userDTO + ", busDTO=" + busDTO + "]";
	}

	
}
