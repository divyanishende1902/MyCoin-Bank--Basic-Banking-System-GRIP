package com.mycoin.Mycoin.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name= "email")
    private String email;
    
    @Column(name = "balance")
    private double balance;

    @OneToMany (mappedBy = "sender")
    private List<Transfer> transfersSent;

    @OneToMany(mappedBy = "receiver")
    private List<Transfer> transfersReceived;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transfer> getTransfersSent() {
		return transfersSent;
	}

	public List<Transfer> getTransfersReceived() {
		return transfersReceived;
	}

	public void setTransfersReceived(List<Transfer> transfersReceived) {
		this.transfersReceived = transfersReceived;
	}

	public void setTransfersSent(List<Transfer> transfersSent) {
		this.transfersSent = transfersSent;
	}

	

    
}

