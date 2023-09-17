package com.mycoin.Mycoin.controller;



    
   import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycoin.Mycoin.entities.Customer;
import com.mycoin.Mycoin.entities.Transfer;
import com.mycoin.Mycoin.exception.InsufficientBalanceException;
import com.mycoin.Mycoin.service.CustomerService;
import com.mycoin.Mycoin.service.TransferService;

import java.util.ArrayList;
import java.util.List;



   

    @Controller
    @RequestMapping("/transfers")
    public class TransferController {

        private final CustomerService customerService;
        private final TransferService transferService;
        
        

       public TransferController(CustomerService customerService, TransferService transferService) {
			super();
			this.customerService = customerService;
			this.transferService = transferService;
		}

	
       
       @GetMapping("/transactionList")
       public String getAllTransactions(Model model) {
           // Get the list of transactions from the service
           List<Transfer> transactions1 = transferService.getAllTransactions();

           model.addAttribute("transactions1", transactions1);

           return "transactionList";
       }
       
       
      

    // Display the transfer form
      @GetMapping("/transfer")
       public String showTransferForm(Model model) {
           // Fetch the list of customers for sender and receiver dropdowns
          List<Customer> customers = customerService.getAllCustomers();
           model.addAttribute("customers", customers);
           
           return "transaction";
          
        
      }

       // Process the transfer request
       @PostMapping("/transfer")
       public String processTransfer(
               @RequestParam Long senderId,
               @RequestParam Long receiverId,
               @RequestParam double amount,
               RedirectAttributes redirectAttributes) {
    	   
    	   

           try {
               // Perform the money transfer using the transfer service
               transferService.makeTransfer(senderId, receiverId, amount);

               // Set a success message to display on the transfer form
               redirectAttributes.addFlashAttribute("successMessage", "Transfer successful!");
           } catch (InsufficientBalanceException e) {
               // Handle insufficient balance exception
               redirectAttributes.addFlashAttribute("errorMessage", "Insufficient balance!");
           } catch (EntityNotFoundException e) {
               // Handle customer not found exception
               redirectAttributes.addFlashAttribute("errorMessage", "Customer not found!");
           } catch (Exception e) {
               // Handle other exceptions
               redirectAttributes.addFlashAttribute("errorMessage", "An error occurred during the transfer.");
           }
           
           return "transaction";


       }
     
       
       @GetMapping("/customers/{customerId}/transactions")
       
       public String getTransactionsForCustomer(@PathVariable Long customerId, Model model) {
           // Retrieve the customer by ID
           Customer customer = customerService.getCustomerById(customerId);

           if (customer != null) {
               // Get the list of transactions for the customer
               List<Transfer> transactions = transferService.getTransactionsForCustomer(customer);

               // Add the transactions to the model
               model.addAttribute("transactions", transactions);
               model.addAttribute("customer", customer);

               // Return the name of the Thymeleaf template for displaying transactions
               return "transaction-history";
           } else {
               // You can display an error message or redirect to an error page
        	   System.out.println("Customer Not found with provided Id");
               return "customerNotFound"; // Return an appropriate error template
           }
       }


    }

    

