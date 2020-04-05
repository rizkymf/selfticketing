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
import com.lawencon.ticketing.model.TicketType;
import com.lawencon.ticketing.service.TicketTypeService;

@RestController
public class TicketTypeController extends BaseController {
	
	@Autowired
	private TicketTypeService typeServ;
	
	@GetMapping("/tickettype")
	public ResponseEntity<List<TicketType>> showAllType(@RequestHeader("Authorization") String auth){
		List<TicketType> ticketList = new ArrayList<>();
		try {
			ticketList = typeServ.showAllType(authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(ticketList, HttpStatus.OK);
	}
	
	@GetMapping("/tickettype/{id}")
	public ResponseEntity<TicketType> showType(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth){
		TicketType ticketPrice;
		try {
			ticketPrice = typeServ.showTypeById(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketPrice, HttpStatus.CREATED);
	}
	
	@PostMapping("/tickettype")
	public ResponseEntity<String> newType(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<TicketType> body = Arrays.asList(new ObjectMapper().readValue(content, TicketType[].class));
			for(TicketType val : body) {
				typeServ.newType(val, authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PatchMapping("/tickettype")
	public ResponseEntity<String> updateType(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<TicketType> body = Arrays.asList(new ObjectMapper().readValue(content, TicketType[].class));
			for(TicketType val : body) {
				typeServ.updateType(val, authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<String>("Update berhasil!", HttpStatus.OK);
	}
	
	@DeleteMapping("/tickettype/{id}")
	public ResponseEntity<String> deleteType(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth){
		try {
			typeServ.deleteType(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Delete success", HttpStatus.OK);
	}

}
