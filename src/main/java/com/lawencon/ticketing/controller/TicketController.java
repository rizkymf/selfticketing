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
import com.lawencon.ticketing.model.Seats;
import com.lawencon.ticketing.model.Ticket;
import com.lawencon.ticketing.model.TicketSeat;
import com.lawencon.ticketing.service.SeatService;
import com.lawencon.ticketing.service.TicketService;

@RestController
public class TicketController extends BaseController {
	
	@Autowired
	private TicketService ticketServ;
	
	@Autowired
	private SeatService seatServ;

	@GetMapping("/ticket")
	public ResponseEntity<List<Ticket>> showAllTicket(@RequestHeader("Authorization") String auth){
		List<Ticket> ticketList = new ArrayList<>();
		try {
			ticketList = ticketServ.showAllTicket(authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<List<Ticket>>(ticketList, HttpStatus.OK);
	}
	
	@GetMapping("/ticket/{id}")
	public ResponseEntity<Ticket> showTicket(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth){
		Ticket ticketPrice;
		try {
			ticketPrice = ticketServ.showTicketById(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketPrice, HttpStatus.CREATED);
	}
	
	@PostMapping("/ticket")
	public ResponseEntity<String> newTicket(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<TicketSeat> body = Arrays.asList(new ObjectMapper().readValue(content, TicketSeat[].class));
			for(TicketSeat val : body) {
				ticketServ.newTicket(val.getTicket(), authorize(auth)[0], authorize(auth)[1]);
				for(Seats x : val.getSeat()) {
					x.setTicket(val.getTicket());
					seatServ.newSeat(x, authorize(auth)[0], authorize(auth)[1]);
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Tiket berhasil ditambahkan!", HttpStatus.CREATED);
	}
	
	@PatchMapping("/ticket")
	public ResponseEntity<String> updateTicket(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<Ticket> body = Arrays.asList(new ObjectMapper().readValue(content, Ticket[].class));
			for(Ticket val : body) {
				ticketServ.updateTicket(val, authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<String>("Update berhasil!", HttpStatus.OK);
	}
	
	@DeleteMapping("/ticket/{id}")
	public ResponseEntity<String> deleteTicket(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth){
		try {
			ticketServ.deleteTicketById(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Delete success", HttpStatus.OK);
	}
}
