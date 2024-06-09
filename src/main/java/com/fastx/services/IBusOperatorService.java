package com.fastx.services;

import java.util.List;

import com.fastx.dto.BusOperatorDTO;
import com.fastx.exceptions.BusOperatorNotFoundException;
import com.fastx.exceptions.ResourceNotFoundException;

public interface IBusOperatorService {

	BusOperatorDTO createBusOperator(BusOperatorDTO busOperatorDTO);
	BusOperatorDTO updateBusOperator(BusOperatorDTO busOperatorDTO, Integer operatorid) throws ResourceNotFoundException, BusOperatorNotFoundException;
	void deleteBusOperator(Integer operatorid) throws ResourceNotFoundException, BusOperatorNotFoundException;
	BusOperatorDTO getBusOperatorById(Integer operatorid) throws ResourceNotFoundException, BusOperatorNotFoundException;
	List<BusOperatorDTO> getAllBusOperators();
}
