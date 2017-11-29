package net.atos.Codex_IOT.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.atos.Codex_IOT.pojo.Customer;

public interface CustomerService {

	public Customer findById(long id);
	
	
	public List<Customer> findAll();
}
