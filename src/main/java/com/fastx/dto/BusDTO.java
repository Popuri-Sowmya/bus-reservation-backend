package com.fastx.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fastx.models.Booking;
import com.fastx.models.Bus;
import com.fastx.models.BusOperator;
import com.fastx.models.Route;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class BusDTO {
	@NotNull
	private int busid;
	@NotEmpty
	private String busName;
	@NotEmpty
	private String driverName;
	@NotEmpty
	private String busType;
	@NotEmpty
	private String busRegNumber;
	@NotEmpty
	private String routeFrom;
	@NotEmpty
	private String routeTo;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime arrivalTime;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime departureTime;
	@Positive
	private int totalSeats;
	@PositiveOrZero
	private int availableSeats;
	@Positive
	private int farePerSeat;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate busJourneyDate;
	@NotEmpty
	@URL
	private String trackingURL;
	private RouteDTO route;
	
	public BusDTO() {
		
	}

	public BusDTO(@NotNull int busid, @NotEmpty String busName, @NotEmpty String driverName, @NotEmpty String busType,
			@NotEmpty String busRegNumber, @NotEmpty String routeFrom, @NotEmpty String routeTo, LocalTime arrivalTime,
			LocalTime departureTime, @Positive int totalSeats, @PositiveOrZero int availableSeats,
			@Positive int farePerSeat, LocalDate busJourneyDate, @NotEmpty @URL String trackingURL, RouteDTO route) {
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

	public RouteDTO getRoute() {
		return route;
	}

	public void setRoute(RouteDTO route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "BusDTO [busid=" + busid + ", busName=" + busName + ", driverName=" + driverName + ", busType=" + busType
				+ ", busRegNumber=" + busRegNumber + ", routeFrom=" + routeFrom + ", routeTo=" + routeTo
				+ ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", totalSeats=" + totalSeats
				+ ", availableSeats=" + availableSeats + ", farePerSeat=" + farePerSeat + ", busJourneyDate="
				+ busJourneyDate + ", trackingURL=" + trackingURL + ", route=" + route + "]";
	}
}
