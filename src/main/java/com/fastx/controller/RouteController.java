package com.fastx.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastx.dto.RouteDTO;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.exceptions.RouteNotFoundException;
import com.fastx.services.IRouteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/route")
public class RouteController {
	@Autowired
	private IRouteService RouteService;
	
	//create
	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO){
		RouteDTO createdRoute = this.RouteService.createRoute(routeDTO);
		return new ResponseEntity<RouteDTO>(createdRoute,HttpStatus.CREATED);
	}
	
	//error
	//update
	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("/update/{routeId}")
	public ResponseEntity<RouteDTO> updateRoute(@RequestBody RouteDTO RouteDTO,@PathVariable Integer routeId) throws ResourceNotFoundException, RouteNotFoundException{
		RouteDTO updatedRoute = this.RouteService.updateRoute(RouteDTO,routeId);
		return new ResponseEntity<RouteDTO>(updatedRoute,HttpStatus.OK);
	}
	
	//delete
	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/delete/{routeId}")
	public ResponseEntity<?> deleteRoute(@PathVariable Integer routeId) throws ResourceNotFoundException, RouteNotFoundException{
		this.RouteService.deleteRoute(routeId);
		return new ResponseEntity(Map.of("message","Route Deleted Successfully"),HttpStatus.OK);
	}
	
	//get
	//all
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER') OR hasAuthority('ROLE_OPERATOR')")
	@GetMapping("/get/{routeId}")
	public ResponseEntity<RouteDTO> getRoute(@PathVariable Integer routeId) throws ResourceNotFoundException, RouteNotFoundException{
		RouteDTO RouteDTO = this.RouteService.getRoute(routeId);
		return new ResponseEntity<RouteDTO>(RouteDTO,HttpStatus.OK);
	}
	
	//getall
	//all
	@GetMapping("/getAll")
	public ResponseEntity<List<RouteDTO>> getAllRoutees() throws ResourceNotFoundException{
		List<RouteDTO> routes = this.RouteService.getRoutes();
		return ResponseEntity.ok(routes);
	}
}