package com.mycoin.Mycoin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycoin.Mycoin.entities.Customer;
import com.mycoin.Mycoin.entities.Transfer;
import com.mycoin.Mycoin.service.CustomerService;
import com.mycoin.Mycoin.service.TransferService;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private TransferService transferService;
    
    
    
    // this method will call index page
    @GetMapping("/index")
    public String showIndexPage() {
        return "index"; // html page 
    }

   
    // will give you List of  customers
    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        
        //*****************************************88
        // Fetch transaction history for each customer
        List<List<Transfer>> transactions = new ArrayList<>();
        for (Customer customer : customers) {
            List<Transfer> customerTransactions = transferService.getTransactionsForCustomer(customer);
            transactions.add(customerTransactions);
        }
        model.addAttribute("transactions", transactions);
        
        return "Customers"; //html page
    }

    
  //this method will call method by Id (url will be localhost:8080/customers/1)  
    @GetMapping("/customers/{id}")
    public String viewCustomerDetails(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "Details"; //html page
    }
    
}

