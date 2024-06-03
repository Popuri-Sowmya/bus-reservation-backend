package com.fastx.serviceimpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastx.dto.BusDTO;
import com.fastx.exceptions.BusNotFoundException;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.services.IBusService;

@SpringBootTest
public class BusServiceCRUDTest {
	public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private IBusService busService;
	
	@Disabled
	@Test
	public void getByIdTest() throws ResourceNotFoundException, BusNotFoundException {
		BusDTO bus = this.busService.getBus(3);
		LOGGER.log(Level.INFO, "bus with id 3 is" + bus);
	}
	
	@Disabled
	@Test
	public void getAllTest() throws ResourceNotFoundException, BusNotFoundException {
		List<BusDTO> buses = this.busService.getBuses();
		LOGGER.log(Level.INFO, "List of buses in record are" + buses);
	}
}
