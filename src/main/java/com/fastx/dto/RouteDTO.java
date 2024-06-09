package com.fastx.dto;

import java.util.ArrayList;
import java.util.List;

import com.fastx.models.Booking;
import com.fastx.models.Bus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RouteDTO {
	@NotNull
	private int routeid;
	@NotEmpty
	private String routeFrom;
	@NotEmpty
	private String routeTo;
	@Positive
	private Integer distance;
	
	public RouteDTO() {
	}

	public RouteDTO(@NotNull int routeid, @NotEmpty String routeFrom, @NotEmpty String routeTo,
			@Positive Integer distance) {
		super();
		this.routeid = routeid;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.distance = distance;
	}

	public int getRouteid() {
		return routeid;
	}

	public void setRouteid(int routeid) {
		this.routeid = routeid;
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

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "RouteDTO [routeid=" + routeid + ", routeFrom=" + routeFrom + ", routeTo=" + routeTo + ", distance="
				+ distance + "]";
	}
}
