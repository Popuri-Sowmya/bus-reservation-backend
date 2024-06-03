package com.fastx.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastx.dto.RouteDTO;
import com.fastx.models.Route;
import com.fastx.repositories.RouteRepository;
import com.fastx.services.IRouteService;
import com.fastx.exceptions.RouteNotFoundException;

@Service
public class RouteServiceImpl implements IRouteService{
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public RouteDTO createRoute(RouteDTO routeDTO) {
		Route route = this.modelMapper.map(routeDTO, Route.class);
		Route addedRoute = this.routeRepository.save(route);
		return this.modelMapper.map(addedRoute,RouteDTO.class);
	}

	@Override
	public RouteDTO updateRoute(RouteDTO routeDTO,Integer routeId) throws RouteNotFoundException {
		Optional<Route> routeop = this.routeRepository.findById(routeId);
		Route Route = routeop.get();
		if(routeop.isEmpty()) {
			throw new RouteNotFoundException("Route not found while updating with ID: "+routeId);
		}
		Route.setRouteFrom(routeDTO.getRouteFrom());
		Route.setRouteTo(routeDTO.getRouteTo());
		Route.setDistance(routeDTO.getDistance());
		Route addedRoute = this.routeRepository.save(Route);
		return this.modelMapper.map(addedRoute,RouteDTO.class);
	}

	@Override
	public void deleteRoute(Integer routeId) throws RouteNotFoundException {
		Optional<Route> routeop = this.routeRepository.findById(routeId);
		Route Route = routeop.get();
		if(routeop.isEmpty()) {
			throw new RouteNotFoundException("Route not found while deleting with ID: "+routeId);
		}
		this.routeRepository.delete(Route);
	}

	@Override
	public RouteDTO getRoute(Integer routeId) throws RouteNotFoundException {
		Optional<Route> routeop = this.routeRepository.findById(routeId);
		Route Route = routeop.get();
		if(routeop.isEmpty()) {
			throw new RouteNotFoundException("Route not found while updating with ID: "+routeId);
		}
		return this.modelMapper.map(Route,RouteDTO.class);
	}

	@Override
	public List<RouteDTO> getRoutes() {
		List<Route> routes = this.routeRepository.findAll();
		List<RouteDTO> allRoutes = routes.stream().map((route)->this.modelMapper.map(route,RouteDTO.class)).collect(Collectors.toList());
		return allRoutes;
	}

}
