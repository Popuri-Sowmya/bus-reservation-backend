package com.fastx.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fastx.dto.RegisterDTO;
import com.fastx.dto.UserDTO;
import com.fastx.exceptions.BadRequestException;
import com.fastx.exceptions.UserNotFoundException;
import com.fastx.models.User;
import com.fastx.repositories.UserRepository;
import com.fastx.services.IUserService;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added successfully.";
    }

    @Override
    public String register(RegisterDTO dto) {
        if (userRepository.existsByUserName(dto.getUserName())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles("ROLE_USER");
        user.setGender(dto.getGender());
        user.setMobileNumber(dto.getMobileNumber());
        userRepository.save(user);
        return "Registration Successful!";
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        //passwordEncoder.encode
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        if(userDTO.getPassword().length() > 25) {
        	user.setPassword(userDTO.getPassword());
        }
        else{
        	user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        user.setGender(userDTO.getGender());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setRoles(userDTO.getRoles());
        User updatedUser = userRepository.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return userToDto(user);
    }

    @Override
    public void deleteUser(Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    private UserDTO userToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserid(user.getUserid());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setGender(user.getGender());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

	@Override
	public UserDTO getUserByUsername(String username) throws UserNotFoundException {
		User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return userToDto(user);
	}
}