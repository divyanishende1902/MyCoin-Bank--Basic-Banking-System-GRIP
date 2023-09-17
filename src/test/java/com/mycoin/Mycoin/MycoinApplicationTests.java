package com.mycoin.Mycoin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycoin.Mycoin.entities.Customer;
import com.mycoin.Mycoin.entities.Transfer;
import com.mycoin.Mycoin.exception.InsufficientBalanceException;
import com.mycoin.Mycoin.repository.CustomerRepository;
import com.mycoin.Mycoin.repository.TransferRepository;
import com.mycoin.Mycoin.service.CustomerService;

@SpringBootTest
class MycoinApplicationTests {
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private TransferRepository tranferrRepo;
	
	@Autowired
	private TransferRepository transferService;


	@Test
	void contextLoads() {
	}
	
	@Test
	public void testGetCustomerById() {
	    Long customerId = 1L; // Replace with a valid customer ID from your test data
	    Customer customer = customerService.getCustomerById(customerId);
	    assertNotNull(customer);
	    assertEquals(customerId, customer.getId());
	}
    
	
	@Test
	public void testGetAllCustomers() {
	    List<Customer> customers = customerService.getAllCustomers();
	    assertNotNull(customers);
	    assertFalse(customers.isEmpty());
	}
	
	


	@Test
	public void testCustomerNotFound() {
	    // Attempt to retrieve a customer with an invalid ID
	    Long invalidCustomerId = -1L; // An invalid customer ID

	    try {
	        customerService.getCustomerById(invalidCustomerId);
	        fail("Expected EntityNotFoundException was not thrown.");
	    } catch (EntityNotFoundException e) {
	        // The expected exception is caught here.
	    }
	}


}
