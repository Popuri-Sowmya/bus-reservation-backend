package com.fastx.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fastx.dto.BusDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int busid;
	private String busName;
	private String driverName;
	private String busType;
	private String busRegNumber;
	private String routeFrom;
	private String routeTo;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private int totalSeats;
	private int availableSeats;
	private int farePerSeat;
	private LocalDate busJourneyDate;
	private String trackingURL;
	//many buses can travel in one route
	@ManyToOne
	@JoinColumn(name="route_id")
	private Route route;
	
	@OneToMany(mappedBy="bus",cascade = CascadeType.ALL)
	private List<Booking> bookingList = new ArrayList<Booking>();
	
	public Bus() {
		
	}

	public Bus(int busid, String busName, String driverName, String busType, String busRegNumber, String routeFrom,
			String routeTo, LocalTime arrivalTime, LocalTime departureTime, int totalSeats, int availableSeats,
			int farePerSeat, LocalDate busJourneyDate, String trackingURL, Route route, List<Booking> bookingList) {
		super();
		this.busid = busid;
		this.busName = busName;
		this.driverName = driverName;
		this.busType = busType;
		this.busRegNumber = busRegNumber;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.farePerSeat = farePerSeat;
		this.busJourneyDate = busJourneyDate;
		this.trackingURL = trackingURL;
		this.route = route;
		this.bookingList = bookingList;
	}

	public int getBusid() {
		return busid;
	}

	public void setBusid(int busid) {
		this.busid = busid;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusRegNumber() {
		return busRegNumber;
	}

	public void setBusRegNumber(String busRegNumber) {
		this.busRegNumber = busRegNumber;
	}

	public String getRouteFrom() {
		return routeFrom;
	}

	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getFarePerSeat() {
		return farePerSeat;
	}

	public void setFarePerSeat(int farePerSeat) {
		this.farePerSeat = farePerSeat;
	}

	public LocalDate getBusJourneyDate() {
		return busJourneyDate;
	}

	public void setBusJourneyDate(LocalDate busJourneyDate) {
		this.busJourneyDate = busJourneyDate;
	}

	public String getTrackingURL() {
		return trackingURL;
	}

	public void setTrackingURL(String trackingURL) {
		this.trackingURL = trackingURL;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public List<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}

	@Override
	public String toString() {
		return "Bus [busid=" + busid + ", busName=" + busName + ", driverName=" + driverName + ", busType=" + busType
				+ ", busRegNumber=" + busRegNumber + ", routeFrom=" + routeFrom + ", routeTo=" + routeTo
				+ ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", totalSeats=" + totalSeats
				+ ", availableSeats=" + availableSeats + ", farePerSeat=" + farePerSeat + ", busJourneyDate="
				+ busJourneyDate + ", trackingURL=" + trackingURL + ", route=" + route + ", bookingList=" + bookingList
				+ "]";
	}
}