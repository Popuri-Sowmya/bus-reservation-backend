package com.fastx.services;

import java.util.List;

import com.fastx.dto.RouteDTO;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.exceptions.RouteNotFoundException;

public interface IRouteService {
	//create
	RouteDTO createRoute(RouteDTO routeDTO);
	//update
	RouteDTO updateRoute(RouteDTO routeDTO, Integer routeId) throws ResourceNotFoundException, RouteNotFoundException;
	//delete
	void deleteRoute(Integer routeId) throws ResourceNotFoundException, RouteNotFoundException;
	//get
	RouteDTO getRoute(Integer routeId) throws ResourceNotFoundException, RouteNotFoundException;
	//get all
	List<RouteDTO> getRoutes();
}
