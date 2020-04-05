package com.lawencon.ticketing.service;

import java.util.List;

import com.lawencon.ticketing.model.Vouchers;

public interface VoucherService {
	abstract List<Vouchers> showAllVoucher(String uname, String pwd) throws Exception;
	abstract Vouchers showVoucherById(Long id, String uname, String pwd) throws Exception;
	abstract void newVoucher(Vouchers voucher, String uname, String pwd) throws Exception;
	abstract Vouchers updateVoucher(Vouchers voucher, String uname, String pwd) throws Exception;
	abstract void deleteAllVoucher(String uname, String pwd) throws Exception;
	abstract void deleteVoucher(Long id, String uname, String pwd) throws Exception;
}
