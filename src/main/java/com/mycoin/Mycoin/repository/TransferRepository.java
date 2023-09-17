package com.mycoin.Mycoin.repository;

import com.mycoin.Mycoin.entities.Customer;
import com.mycoin.Mycoin.entities.Transfer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TransferRepository  extends JpaRepository<Transfer, Long> {

	List<Transfer> findBySenderOrReceiver(Customer customer, Customer customer2);

	
}
