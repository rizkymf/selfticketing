package com.lawencon.ticketing.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trxId;

	private String trxCode;

	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticketId;
	
	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seats seat;

	@OneToOne
	@JoinColumn(name = "user_id")
	private Users userId;
	
	private LocalDate trxDate = LocalDate.now();
	private LocalDateTime trxTime = LocalDateTime.now();
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "voucher_id")
	private Vouchers voucher;

	public Long getTrxId() {
		return trxId;
	}

	public void setTrxId(Long trxId) {
		this.trxId = trxId;
	}

	public String getTrxCode() {
		return trxCode;
	}

	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}

	public Ticket getTicketId() {
		return ticketId;
	}

	public void setTicketId(Ticket ticketId) {
		this.ticketId = ticketId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public LocalDate getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(LocalDate trxDate) {
		this.trxDate = trxDate;
	}

	public LocalDateTime getTrxTime() {
		return trxTime;
	}

	public void setTrxTime(LocalDateTime trxTime) {
		this.trxTime = trxTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Vouchers getVoucher() {
		return voucher;
	}

	public void setVoucher(Vouchers voucher) {
		this.voucher = voucher;
	}

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}
	
	
	

}
