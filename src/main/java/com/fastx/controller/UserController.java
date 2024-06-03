package com.fastx.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastx.dto.AuthRequest;
import com.fastx.dto.UserDTO;
import com.fastx.exceptions.ResourceNotFoundException;
import com.fastx.exceptions.UserNotFoundException;
import com.fastx.models.User;
import com.fastx.services.IUserService;
import com.fastx.services.JwtService;
import com.fastx.dto.RegisterDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IUserService userService;
	 @PostMapping("/new")
	    public String addNewUser(@RequestBody User user) {
	        return userService.addUser(user);
	    }
	 @PostMapping("/authenticate")
	    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(authRequest.getUsername());
	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }
	    }
	
	 @PostMapping("/register")
		public ResponseEntity<String> register(@RequestBody RegisterDTO dto)
		{
			String value = userService.register(dto);
			return new ResponseEntity<>(value, HttpStatus.CREATED);
		}
	 
	@PutMapping("/updateuser/{userid}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable("userid")Integer userid) throws UserNotFoundException{
		UserDTO updatedUser=this.userService.updateUser(userDTO,userid);
		return ResponseEntity.ok(updatedUser);
	}
	
	//admin
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("userid") Integer userid) throws UserNotFoundException{
		this.userService.deleteUser(userid);
		return new ResponseEntity(Map.of("message","User Deleted Successfylly"),HttpStatus.OK);
		}
	
	//admin
	 @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping("/get/{userid}")
	public ResponseEntity<UserDTO>getSingleUser(@PathVariable("userid") Integer userid) throws UserNotFoundException{
		return ResponseEntity.ok(this.userService.getUserById(userid));
	}
	
	 
	@GetMapping("/username")
	public ResponseEntity<UserDTO>getbyUserName(@RequestParam String username) throws UserNotFoundException{
		return ResponseEntity.ok(this.userService.getUserByUsername(username));
	}
	
	//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
}