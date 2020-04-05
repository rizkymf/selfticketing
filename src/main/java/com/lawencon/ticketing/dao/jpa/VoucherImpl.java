package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.VoucherDao;
import com.lawencon.ticketing.model.Vouchers;

@Repository("voucher_repo_jpa")
public class VoucherImpl implements VoucherDao {

	@Autowired
	VoucherRepo vRepo;

	@Override
	public List<Vouchers> showAllVoucher() {
		return vRepo.findAll();
	}

	@Override
	public Vouchers showVoucherById(Long id) {
		return vRepo.findById(id).orElse(null);
	}

	@Override
	public void newVoucher(Vouchers voucher) {
		vRepo.save(voucher);
	}

	@Override
	public Vouchers updateVoucher(Vouchers voucher) {
		voucher = vRepo.save(voucher);
		return voucher;
	}

	@Override
	public void deleteAllVoucher() {
		vRepo.deleteAll();
	}

	@Override
	public void deleteVoucher(Long id) {
		vRepo.deleteById(id);
	}
	
	
}
