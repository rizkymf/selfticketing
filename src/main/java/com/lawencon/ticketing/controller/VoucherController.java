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
import com.lawencon.ticketing.model.Vouchers;
import com.lawencon.ticketing.service.VoucherService;

@RestController
public class VoucherController extends BaseController {

	@Autowired
	private VoucherService vServ;
	
	@GetMapping("/voucher")
	public ResponseEntity<List<Vouchers>> showAllVoucher(@RequestHeader("Authorization") String auth){
		List<Vouchers> voucherList = new ArrayList<>();
		try {
			voucherList = vServ.showAllVoucher(authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(voucherList, HttpStatus.OK);
	}
	
	@GetMapping("/voucher/{id}")
	public ResponseEntity<Vouchers> showVoucher(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth){
		Vouchers voucher;
		try {
			voucher = vServ.showVoucherById(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(voucher, HttpStatus.CREATED);
	}
	
	@PostMapping("/voucher")
	public ResponseEntity<String> newVoucher(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<Vouchers> body = Arrays.asList(new ObjectMapper().readValue(content, Vouchers[].class));
			for(Vouchers val : body) {
				vServ.newVoucher(val, authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PatchMapping("/voucher")
	public ResponseEntity<String> updateVoucher(@RequestBody String content, @RequestHeader("Authorization") String auth){
		try {
			List<Vouchers> body = Arrays.asList(new ObjectMapper().readValue(content, Vouchers[].class));
			for(Vouchers val : body) {
				vServ.updateVoucher(val, authorize(auth)[0], authorize(auth)[1]);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<String>("Update berhasil!", HttpStatus.OK);
	}
	
	@DeleteMapping("/voucher/{id}")
	public ResponseEntity<String> deleteVoucher(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth){
		try {
			vServ.deleteVoucher(id, authorize(auth)[0], authorize(auth)[1]);
		} catch (Exception e) {
			return new ResponseEntity<String>("Terjadi kesalahan, " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Delete success", HttpStatus.OK);
	}
}
