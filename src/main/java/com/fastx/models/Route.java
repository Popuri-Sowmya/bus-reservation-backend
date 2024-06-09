package com.fastx.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class Route {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int routeid;
	private String routeFrom;
	private String routeTo;
	private Integer distance;
	
	//one route can have many buses
	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
	private List<Bus> busList=new ArrayList<>();
	
	public Route() {
		
	}

	public Route(int routeid, String routeFrom, String routeTo, Integer distance, List<Bus> busList) {
		super();
		this.routeid = routeid;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.distance = distance;
		this.busList = busList;
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

	public List<Bus> getBusList() {
		return busList;
	}

	public void setBusList(List<Bus> busList) {
		this.busList = busList;
	}

	@Override
	public String toString() {
		return "Route [routeid=" + routeid + ", routeFrom=" + routeFrom + ", routeTo=" + routeTo + ", distance="
				+ distance + ", busList=" + busList + "]";
	}
}