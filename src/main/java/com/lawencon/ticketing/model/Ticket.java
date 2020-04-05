package com.lawencon.ticketing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;

	@ManyToOne
	@JoinColumn(name = "typeId")
	private TicketType typeId;

	private String ticketName;
	private int price;
	
//	@ManyToOne
//	@JoinColumn(name = "seatId")
//	private Seats seat;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public TicketType getTypeId() {
		return typeId;
	}

	public void setTypeId(TicketType typeId) {
		this.typeId = typeId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

//	public Seats getSeat() {
//		return seat;
//	}
//
//	public void setSeat(Seats seat) {
//		this.seat = seat;
//	}

}
