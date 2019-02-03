package com.sales.management.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sales.management.data.entities.User;
import com.sales.management.data.respositories.UserRespostory;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserRespostory _userRepository;

	@GetMapping
	public List<User> getAll() {
		return _userRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> buscar(@PathVariable Long id) {
		Optional<User> user = _userRepository.findById(id);
		
		if (user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(user.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User adicionar(@Valid @RequestBody User user) {
		Optional<User> existingUser = _userRepository.findByUsername(user.getUsername());
		
		if (existingUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserName is already taken.");
		}
		
		return _userRepository.save(user);
	}
	
	// TODO: implement put and delete
}
