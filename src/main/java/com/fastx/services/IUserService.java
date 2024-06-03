package com.fastx.services;

import java.util.List;

import com.fastx.dto.RegisterDTO;
import com.fastx.dto.UserDTO;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.exceptions.UserNotFoundException;
import com.fastx.models.User;

public interface IUserService {
	public String addUser(User user); 
	//UserDTO createUser(UserDTO userDTO);
	UserDTO updateUser(UserDTO user,Integer userid) throws UserNotFoundException;
	void deleteUser(Integer userid) throws  UserNotFoundException;
	UserDTO getUserById(Integer userid) throws UserNotFoundException;
	List<UserDTO> getAllUsers();
	public String register(RegisterDTO dto);
	UserDTO getUserByUsername(String username) throws UserNotFoundException;
}