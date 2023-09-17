package com.mycoin.Mycoin.service;

import java.util.List;

import com.mycoin.Mycoin.entities.Customer;
import com.mycoin.Mycoin.entities.Transfer;

public interface TransferService {
	
	 Transfer makeTransfer(Long senderId, Long receiverId, double amount);
	
	 List<Transfer> getTransactionsForCustomer(Customer customer);

	 List<Transfer> getAllTransactions();


}
