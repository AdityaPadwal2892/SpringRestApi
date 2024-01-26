package com.codewithaditya.fullstackbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaditya.fullstackbackend.model.User;
import com.codewithaditya.fullstackbackend.repository.UserRepository;
import java.util.Optional;
import com.codewithaditya.fullstackbackend.exception.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/User")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	@GetMapping("/Users")
	List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/User/{id}")
	User getUserById(@PathVariable Long id)
	{
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
				
	}
	
	@PutMapping("/User/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
	    return userRepository.findById(id)
	            .map(User -> {
	                User.setUsername(newUser.getUsername());
	                User.setName(newUser.getName());
	                User.setEmail(newUser.getEmail());
	                return userRepository.save(User);
	            })
	            .orElseThrow(() -> new UserNotFoundException(id));
	}
	
	@DeleteMapping("/User/{id}")
	String deleteUser(@PathVariable Long id)
	{
		if(!userRepository.existsById(id))
		{
			throw new UserNotFoundException(id);
			
		}
		userRepository.deleteById(id);
		return "User with id "+id+"has been deleted success";
	}

	

}
