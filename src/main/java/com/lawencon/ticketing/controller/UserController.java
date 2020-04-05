package com.lawencon.ticketing.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.ticketing.model.Users;
import com.lawencon.ticketing.service.UserService;

@RestController
public class UserController extends BaseController {

	@Autowired
	UserService usrServ;
	
	@GetMapping("/user")
	public ResponseEntity<List<Users>> showAllUser(@RequestHeader("Authorization") String auth){
		List<Users> userList = new ArrayList<>();
		try {
			userList = usrServ.showAllUser(authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Users> showUser(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth){
		Users user;
		try {
			user = usrServ.showUserById(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> newUser(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<Users> body = Arrays.asList(new ObjectMapper().readValue(content, Users[].class));
			for(Users val : body) {
				usrServ.newUser(val, authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("User baru berhasil dibuat!", HttpStatus.CREATED);
	}
	
	@PatchMapping("/user")
	public ResponseEntity<String> updateUser(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<Users> body = Arrays.asList(new ObjectMapper().readValue(content, Users[].class));
			for(Users val : body) {
				usrServ.updateUser(val, authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<String>("Update berhasil!", HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth){
		try {
			usrServ.deleteUser(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Delete success", HttpStatus.OK);
	}
}
