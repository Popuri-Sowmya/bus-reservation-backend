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

import com.fastx.dto.BusOperatorDTO;
import com.fastx.exceptions.BusOperatorNotFoundException;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.services.IBusOperatorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/operators") 
public class BusOperatorController {
	@Autowired
	private IBusOperatorService busOperatorService;

	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/")
	public ResponseEntity<BusOperatorDTO> createBusOperator(@RequestBody BusOperatorDTO busOperatorDTO){
		BusOperatorDTO createBusOperatorDTO=this.busOperatorService.createBusOperator(busOperatorDTO);
		return new ResponseEntity<>(createBusOperatorDTO,HttpStatus.CREATED);
	}
	
	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("/update/{operatorid}")
	public ResponseEntity<BusOperatorDTO> updateBusOperator(@RequestBody BusOperatorDTO busOperatorDTO,@PathVariable("operatorid")Integer operatorid) throws ResourceNotFoundException, BusOperatorNotFoundException{
		BusOperatorDTO updatedBusOperator=this.busOperatorService.updateBusOperator(busOperatorDTO,operatorid);
		return ResponseEntity.ok(updatedBusOperator);
	}

	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/delete/{operatorid}")
	public ResponseEntity<BusOperatorDTO> deleteBusOperator(@PathVariable("operatorid") Integer operatorid) throws ResourceNotFoundException, BusOperatorNotFoundException{
		this.busOperatorService.deleteBusOperator(operatorid);
		return new ResponseEntity(Map.of("message","BusOperator Deleted Successfully"),HttpStatus.OK);
		}
	
	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/get/{operatorid}")
	public ResponseEntity<BusOperatorDTO>getSingleBusOperator(@PathVariable("operatorid") Integer operatorid) throws ResourceNotFoundException, BusOperatorNotFoundException{
		return ResponseEntity.ok(this.busOperatorService.getBusOperatorById(operatorid));
	}
	
	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/getAll")
	public ResponseEntity<List<BusOperatorDTO>> getAllBusOperators(){
		return ResponseEntity.ok(this.busOperatorService.getAllBusOperators());
	}
	
}
