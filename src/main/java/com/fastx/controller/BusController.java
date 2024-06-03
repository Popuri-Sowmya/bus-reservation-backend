package com.fastx.controller;

import java.util.List;
import java.util.Map;


import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fastx.dto.BusDTO;
import com.fastx.models.Bus;
import com.fastx.exceptions.BusNotFoundException;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.repositories.BusRepository;
import com.fastx.services.IBusService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/bus")
public class BusController {
    @Autowired
    private IBusService busService;
    
    //create
    //admin and operator
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_OPERATOR')")
    @PostMapping("/")
    public ResponseEntity<BusDTO> createBus(@RequestBody BusDTO busDTO){
        BusDTO createdBus = this.busService.createBus(busDTO);
        return new ResponseEntity<BusDTO>(createdBus,HttpStatus.CREATED);
    }
    
    //update
    //operator
    @PreAuthorize("hasAuthority('ROLE_OPERATOR')")
    @PutMapping("/update/{busId}")
    public ResponseEntity<BusDTO> updateBus(@RequestBody BusDTO busDTO,@PathVariable Integer busId) throws BusNotFoundException, ResourceNotFoundException {
        BusDTO updatedBus = this.busService.updateBus(busDTO,busId);
        return new ResponseEntity<BusDTO>(updatedBus,HttpStatus.OK);
    }
    
    //delete
    //operator
    @PreAuthorize("hasAuthority('ROLE_OPERATOR')")
    @DeleteMapping("/delete/{busId}")
    public ResponseEntity<?> deleteBus(@PathVariable Integer busId) throws BusNotFoundException, ResourceNotFoundException{
        this.busService.deleteBus(busId);
        return new ResponseEntity(Map.of("message","Bus Deleted Successfully"),HttpStatus.OK);
    }
    
    //get
    //admin and operator
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_OPERATOR') OR hasAuthority('ROLE_USER')")
    @GetMapping("/get/{busid}")
    public ResponseEntity<BusDTO> getBus(@PathVariable Integer busid) throws BusNotFoundException, ResourceNotFoundException{
        BusDTO busDTO = this.busService.getBus(busid);
        return new ResponseEntity<BusDTO>(busDTO,HttpStatus.OK);
    }
    
    //getall
    //admin and operator
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_OPERATOR')")
    @GetMapping("/getAll")
    public ResponseEntity<List<BusDTO>> getAllBuses() throws BusNotFoundException{
        List<BusDTO> buses = this.busService.getBuses();
        return ResponseEntity.ok(buses);
    }
    
    //user
    @GetMapping("/search")
    public ResponseEntity<List<BusDTO>> searchBus(@RequestParam String routeFrom, @RequestParam String routeTo, @RequestParam String busJourneyDate) {
        // Parse journeyDate string to LocalDate if needed
    	List<BusDTO> buses = this.busService.searchBus(routeFrom, routeTo, busJourneyDate);
        return ResponseEntity.ok(buses);
    }
    @GetMapping("/byprice")
    public ResponseEntity<List<BusDTO>> getByPrice(@RequestParam int minPrice, @RequestParam int maxPrice) {
        // Parse journeyDate string to LocalDate if needed
    	List<BusDTO> buses = this.busService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(buses);
    }
    @GetMapping("/bytime")
    public ResponseEntity<List<BusDTO>> getByDepartureTime(@RequestParam String departureTime) {
        // Parse journeyDate string to LocalDate if needed
    	List<BusDTO> buses = this.busService.findByDepartureTime(departureTime);
        return ResponseEntity.ok(buses);
    }
    @GetMapping("/bybustype")
    public ResponseEntity<List<BusDTO>> findByBusType(@RequestParam String busType) {
        // Parse journeyDate string to LocalDate if needed
    	List<BusDTO> buses = this.busService.findByBusType(busType);
        return ResponseEntity.ok(buses);
    }
}
