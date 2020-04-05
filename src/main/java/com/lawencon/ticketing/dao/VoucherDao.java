package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.Vouchers;

public interface VoucherDao {

	abstract List<Vouchers> showAllVoucher();
	abstract Vouchers showVoucherById(Long id);
	abstract void newVoucher(Vouchers voucher);
	abstract Vouchers updateVoucher(Vouchers voucher);
	abstract void deleteAllVoucher();
	abstract void deleteVoucher(Long id);
}
