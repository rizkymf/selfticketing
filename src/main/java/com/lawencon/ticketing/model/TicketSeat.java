package com.lawencon.ticketing.model;

import java.util.List;

public class TicketSeat {
	private Ticket ticket;
	private List<Seats> seat;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Seats> getSeat() {
		return seat;
	}

	public void setSeat(List<Seats> seat) {
		this.seat = seat;
	}

}
