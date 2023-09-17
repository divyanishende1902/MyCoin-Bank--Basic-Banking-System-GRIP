package com.mycoin.Mycoin.CustomerServiceImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mycoin.Mycoin.entities.Customer;
import com.mycoin.Mycoin.entities.Transfer;
import com.mycoin.Mycoin.exception.InsufficientBalanceException;
import com.mycoin.Mycoin.repository.CustomerRepository;
import com.mycoin.Mycoin.repository.TransferRepository;
import com.mycoin.Mycoin.service.TransferService;

@Service

public class TransferServiceImpl implements TransferService{
	
	  
    private  final CustomerRepository customerRepo;
  
    private final  TransferRepository transferRepo;

    
    public TransferServiceImpl(CustomerRepository customerRepo, TransferRepository transferRepo) {
		super();
		this.customerRepo = customerRepo;
		this.transferRepo = transferRepo;
	}





	@Override
    @Transactional // Ensure atomic transaction
    public Transfer makeTransfer(Long senderId, Long receiverId, double amount) {
        // Retrieve sender and receiver customers
        Customer sender = customerRepo.findById(senderId)
                .orElseThrow(() -> new EntityNotFoundException("Sender not found"));

        Customer receiver = customerRepo.findById(receiverId)
                .orElseThrow(() -> new EntityNotFoundException("Receiver not found"));

        // Check if sender has sufficient balance
        if (sender.getBalance() < amount) {
            throw new InsufficientBalanceException("Sender does not have sufficient balance");
        }

        // Deduct amount from sender's balance and add it to receiver's balance
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        Transfer transfer = new Transfer();
        transfer.setSender(sender);
        transfer.setReceiver(receiver);
        transfer.setAmount(amount);
        transfer.setDate(new Date()); // Set the date to the current date and time

        // Save the updated sender, receiver, and transfer records
        customerRepo.save(sender);
        customerRepo.save(receiver);
        transferRepo.save(transfer);
        
        return transfer;
    }




// transaction by Id
	
	  @Override
	    public List<Transfer> getTransactionsForCustomer(Customer customer) {
	        List<Transfer> transactions = transferRepo.findBySenderOrReceiver(customer, customer);
	        return transactions;
	    }





	@Override
	public List<Transfer> getAllTransactions() {
		List<Transfer> transactions1 = transferRepo.findAll();
        return transactions1;
	}
	  
	


	

}
