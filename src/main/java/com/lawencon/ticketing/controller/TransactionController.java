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
import com.lawencon.ticketing.model.Transactions;
import com.lawencon.ticketing.service.SeatService;
import com.lawencon.ticketing.service.TransactionService;

@RestController
public class TransactionController extends BaseController {

	@Autowired
	private TransactionService trxServ;

	@Autowired
	private SeatService seatServ;

	@GetMapping("/transaction")
	public ResponseEntity<List<Transactions>> showAllTrx(@RequestHeader("Authorization") String auth) {
		List<Transactions> ticketList = new ArrayList<>();
		try {
			ticketList = trxServ.showAllTrx(authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(ticketList, HttpStatus.OK);
	}

	@GetMapping("/transaction/{id}")
	public ResponseEntity<Transactions> showTrx(@PathVariable("id") Long id,
			@RequestHeader("Authorization") String auth) {
		Transactions ticketPrice;
		try {
			ticketPrice = trxServ.showTrxById(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketPrice, HttpStatus.CREATED);
	}

	@PostMapping("/transaction")
	public ResponseEntity<String> newTrx(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<Transactions> body = Arrays.asList(new ObjectMapper().readValue(content, Transactions[].class));
			for(Transactions val : body) {
				val.setTrxCode(trxServ.codeGenerate(val));
				val.setPrice(trxServ.finalPrice(val));
				trxServ.newTrx(val, authorize(auth)[0], authorize(auth)[1]);
				val.getSeat().setStatus("RESERVED");
				seatServ.updateSeat(new Seats(val.getSeat().getSeatId(), val.getTicketId(), val.getSeat().getSeatCode(), val.getSeat().getStatus()), 
					authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping("/transaction")
	public ResponseEntity<String> updateTrx(@RequestBody String content, @RequestHeader("Authorization") String auth) {
		try {
			List<Transactions> body = Arrays.asList(new ObjectMapper().readValue(content, Transactions[].class));
			for (Transactions val : body) {
				trxServ.updateTrx(val, authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<String>("Update berhasil!", HttpStatus.OK);
	}

	@DeleteMapping("/transaction/{id}")
	public ResponseEntity<String> deleteTrx(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth) {
		try {
			trxServ.deleteTrx(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Delete success", HttpStatus.OK);
	}

}
