package com.lawencon.ticketing.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.VoucherDao;
import com.lawencon.ticketing.model.Vouchers;

@SuppressWarnings("unchecked")
@Repository("voucher_repo_hibernate")
public class VoucherImpl extends BaseHibernate implements VoucherDao {

	@Override
	public List<Vouchers> showAllVoucher() {
		Query q = em.createQuery("from Vouchers");
		return q.getResultList();
	}

	@Override
	public Vouchers showVoucherById(Long id) {
		Query q = em.createQuery("from Vouchers where voucher_id = :voucherId").setParameter("voucherId", id);
		return (Vouchers) q.getResultList().get(0);
	}

	@Override
	public void newVoucher(Vouchers voucher) {
		em.persist(voucher);
	}

	@Override
	public Vouchers updateVoucher(Vouchers voucher) {
		voucher = em.merge(voucher);
		return voucher;
	}

	@Override
	public void deleteAllVoucher() {
		Query q = em.createQuery("delete from Vouchers");
		q.executeUpdate();
	}

	@Override
	public void deleteVoucher(Long id) {
		Query q = em.createQuery("delete from Vouchers where voucher_id = :voucherId").setParameter("voucherId", id);
		q.executeUpdate();
	}

	
}
