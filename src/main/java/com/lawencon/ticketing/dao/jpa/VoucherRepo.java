package com.lawencon.ticketing.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.model.Vouchers;

@Repository
public interface VoucherRepo extends JpaRepository<Vouchers, Long> {

}
