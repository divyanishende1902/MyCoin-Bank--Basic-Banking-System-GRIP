package com.mycoin.Mycoin.CustomerServiceImpl;

import com.mycoin.Mycoin.entities.Customer;
import com.mycoin.Mycoin.entities.Transfer;
import com.mycoin.Mycoin.exception.InsufficientBalanceException;
import com.mycoin.Mycoin.repository.CustomerRepository;
import com.mycoin.Mycoin.repository.TransferRepository;
import com.mycoin.Mycoin.service.CustomerService;
import javax.persistence.EntityNotFoundException;


import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
        
	    private  final CustomerRepository customerRepo;
      
	    private final  TransferRepository transferRepo;

	   
	    public CustomerServiceImpl(CustomerRepository customerRepo, TransferRepository transferRepo) {
	        this.customerRepo = customerRepo;
	        this.transferRepo = transferRepo;
	    }
    
    @Override
    public List<Customer> getAllCustomers() {
         List<Customer> customer = customerRepo.findAll();
         return customer;
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }
 }


