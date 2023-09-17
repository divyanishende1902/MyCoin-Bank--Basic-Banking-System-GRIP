package com.mycoin.Mycoin.service;

import com.mycoin.Mycoin.entities.Customer;
import com.mycoin.Mycoin.entities.Transfer;
import com.mycoin.Mycoin.repository.TransferRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public interface CustomerService {
	

    


    List<Customer> getAllCustomers();

    Customer getCustomerById(Long customerId);



}
