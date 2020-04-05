package com.lawencon.ticketing.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.VoucherDao;
import com.lawencon.ticketing.model.Vouchers;

@Transactional
@Service
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	@Qualifier("voucher_repo_jpa")
	private VoucherDao vDao;

	@Autowired
	private UserService usrServ;

	@Override
	public List<Vouchers> showAllVoucher(String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return vDao.showAllVoucher();
	}

	@Override
	public Vouchers showVoucherById(Long id, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return vDao.showVoucherById(id);
	}

	@Override
	public void newVoucher(Vouchers voucher, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		vDao.newVoucher(voucher);
	}

	@Override
	public Vouchers updateVoucher(Vouchers voucher, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return vDao.updateVoucher(voucher);
	}

	@Override
	public void deleteAllVoucher(String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		vDao.deleteAllVoucher();
	}

	@Override
	public void deleteVoucher(Long id, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		vDao.deleteVoucher(id);
	}

}