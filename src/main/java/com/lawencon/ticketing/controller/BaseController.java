package com.lawencon.ticketing.controller;

import java.util.Base64;

public class BaseController {
	
	public String[] authorize(String auth) {
		byte[] decodedBytes = Base64.getDecoder().decode(auth);
		String[] decodedString = new String(decodedBytes).split(":");
		return decodedString;
	}

}
