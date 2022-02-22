package br.com.senai.resource;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.model.User;
import br.com.senai.repository.UserRepository;

@RestController
@RequestMapping("/v1")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public Iterable<User> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getUser(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		
		return user.isPresent() ?
				ResponseEntity.ok(user.get()) :
				ResponseEntity.notFound().build();
	}
	
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody User user) {
		
		User newUser = userRepository.save(user);
		
		return newUser;
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(
			@PathVariable Long id,
			@RequestBody User body
			){
		
		User user = userRepository.findById(id).get();
		
		BeanUtils.copyProperties(body,user,"id");
		
		userRepository.save(user);
		
		return ResponseEntity.ok(user);
		
	}
	
	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeUser(@PathVariable Long id){
		userRepository.deleteById(id);
	}
	

}
