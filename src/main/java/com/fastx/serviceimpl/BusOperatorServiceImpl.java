package com.fastx.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastx.dto.BusOperatorDTO;
import com.fastx.models.BusOperator;
import com.fastx.repositories.BusOperatorRepository;
import com.fastx.services.IBusOperatorService;
import com.fastx.exceptions.BusOperatorNotFoundException;

@Service
public class BusOperatorServiceImpl implements IBusOperatorService{
	
	@Autowired
	private BusOperatorRepository BusOperatorRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BusOperatorDTO createBusOperator(BusOperatorDTO BusOperatorDTO) {
		BusOperator BusOperator = this.modelMapper.map(BusOperatorDTO, BusOperator.class);
		BusOperator addedBusOperator = this.BusOperatorRepository.save(BusOperator);
		return this.modelMapper.map(addedBusOperator,BusOperatorDTO.class);
	}

	@Override
	public BusOperatorDTO updateBusOperator(BusOperatorDTO BusOperatorDTO,Integer BusOperatorId) throws BusOperatorNotFoundException {
		Optional<BusOperator> BusOperatorop = this.BusOperatorRepository.findById(BusOperatorId);
		BusOperator BusOperator = BusOperatorop.get();
		if(BusOperatorop.isEmpty()) {
			throw new BusOperatorNotFoundException("Bus Operator with id: "+BusOperatorId+" not found while updating");
		}
		BusOperator.setName(BusOperatorDTO.getOperatorName());
		BusOperator.setEmail(BusOperatorDTO.getEmail());
		BusOperator.setMobileNumber(BusOperatorDTO.getMobileNumber());
		BusOperator addedBusOperator = this.BusOperatorRepository.save(BusOperator);
		return this.modelMapper.map(addedBusOperator,BusOperatorDTO.class);
	}

	@Override
	public void deleteBusOperator(Integer BusOperatorId) throws BusOperatorNotFoundException {
		Optional<BusOperator> BusOperatorop = this.BusOperatorRepository.findById(BusOperatorId);
		BusOperator BusOperator = BusOperatorop.get();
		if(BusOperatorop.isEmpty()) {
			throw new BusOperatorNotFoundException("Bus Operator with id: "+BusOperatorId+" not found while deleting");
		}
		this.BusOperatorRepository.delete(BusOperator);
	}

	@Override
	public BusOperatorDTO getBusOperatorById(Integer BusOperatorId) throws BusOperatorNotFoundException {
		Optional<BusOperator> BusOperatorop = this.BusOperatorRepository.findById(BusOperatorId);
		BusOperator BusOperator = BusOperatorop.get();
		if(BusOperatorop.isEmpty()) {
			throw new BusOperatorNotFoundException("Bus Operator with id: "+BusOperatorId+" not found while retrieving by ID");
		}
		return this.modelMapper.map(BusOperator,BusOperatorDTO.class);
	}

	@Override
	public List<BusOperatorDTO> getAllBusOperators() {
		List<BusOperator> BusOperators = this.BusOperatorRepository.findAll();
		List<BusOperatorDTO> allBusOperators = BusOperators.stream().map((BusOperator)->this.modelMapper.map(BusOperator,BusOperatorDTO.class)).collect(Collectors.toList());
		return allBusOperators;
	}
}
