package com.fastx.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fastx.dto.BookingDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer bookingid;
    private String bookingStatus;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private String source;
    private String destination;
    private LocalDate journeyDate;
    private int noOfSeatsBooked;
    private double fare;
    
  //one user can have multiple bookings
  	@ManyToOne(cascade=CascadeType.MERGE)
      @JoinColumn(name = "user_id")
      private User user;
	
	 // one bus can have many booking at a time
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bus_id")
	private Bus bus;
  
	public Booking() {
		
	}
	
	@Override
	public String toString() {
		return "Booking [bookingid=" + bookingid + ", bookingStatus=" + bookingStatus + ", bookingDate=" + bookingDate
				+ ", bookingTime=" + bookingTime + ", source=" + source + ", destination=" + destination
				+ ", journeyDate=" + journeyDate + ", noOfSeatsBooked=" + noOfSeatsBooked + ", fare=" + fare + ", user="
				+ user + ", bus=" + bus + "]";
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



	public int getNoOfSeatsBooked() {
		return noOfSeatsBooked;
	}



	public void setNoOfSeatsBooked(int noOfSeatsBooked) {
		this.noOfSeatsBooked = noOfSeatsBooked;
	}



	public double getFare() {
		return fare;
	}



	public void setFare(double fare) {
		this.fare = fare;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Bus getBus() {
		return bus;
	}



	public void setBus(Bus bus) {
		this.bus = bus;
	}



	public Booking(Integer bookingid, String bookingStatus, LocalDate bookingDate, LocalTime bookingTime, String source,
			String destination, LocalDate journeyDate, int noOfSeatsBooked, double fare, User user, Bus bus) {
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
		this.user = user;
		this.bus = bus;
	}



	public Booking(BookingDTO bookingobj) {
    	this.bookingStatus=bookingobj.getBookingStatus();
    	this.bookingDate=bookingobj.getBookingDate();
    	this.bookingTime=bookingobj.getBookingTime();
    	this.source=bookingobj.getSource();
    	this.destination=bookingobj.getDestination();
    	this.journeyDate=bookingobj.getJourneyDate();
    	this.noOfSeatsBooked=bookingobj.getNoOfSeatsBooked();
    	this.fare=bookingobj.getFare();	
    	
    }
}