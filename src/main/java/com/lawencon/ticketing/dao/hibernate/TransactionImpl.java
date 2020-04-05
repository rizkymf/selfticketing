package com.lawencon.ticketing.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TransactionDao;
import com.lawencon.ticketing.model.Transactions;

@SuppressWarnings("unchecked")
@Repository("transaction_repo_hibernate")
public class TransactionImpl extends BaseHibernate implements TransactionDao {

	@Override
	public List<Transactions> showAllTrx() {
		Query q = em.createQuery("from Transactions");
		return q.getResultList();
	}

	@Override
	public Transactions showTrxById(Long id) {
		Query q = em.createQuery("from Transactions where trx_id = :trxId").setParameter("trxId", id);
		return (Transactions) q.getResultList().get(0);
	}

	@Override
	public void newTrx(Transactions trx) {
		em.persist(trx);
	}

	@Override
	public Transactions updateTrx(Transactions trx) {
		trx = em.merge(trx);
		return trx;
	}

	@Override
	public void deleteAllTrx() {
		Query q = em.createQuery("delete from Transactions");
		q.executeUpdate();
	}

	@Override
	public void deleteTrx(Long id) {
		Query q = em.createQuery("delete from Transactions where trx_id = :trxId").setParameter("trxId", id);
		q.executeUpdate();
	}

	@Override
	public String findCode() {
		Query q = em.createQuery("select trx_code from Transactions order by trx_code desc limit 1");
		return q.getResultList().get(0).toString();
	}

	@Override
	public int findPrice(Transactions trx) {
		Query q = em.createQuery("select price from Ticket where ticket_id = :ticketId").setParameter("ticketId",
				trx.getTicketId().getTicketId());
		return Integer.valueOf(q.getResultList().get(0).toString());
	}

	@Override
	public double findDiscount(Transactions trx) {
		Query q = em.createQuery("select discount from Vouchers where voucher_id = :voucherId")
				.setParameter("voucherId", trx.getVoucher().getVoucherId());
		return Double.valueOf(q.getResultList().get(0).toString());
	}

	@Override
	public boolean findStatus(Transactions trx) {
		Query q = em.createQuery("select status from Seats where ticket_id = :ticketId and seat_code = :seatCode")
				.setParameter("ticketId", trx.getTicketId().getTicketId())
				.setParameter("seatCode", trx.getSeat().getSeatCode());
		if(q.getResultList().get(0).equals("AVAILABLE")) {
			return true;
		} else return false;
	}

}
