package net.atos.Codex_IOT.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.Codex_IOT.dao.CustomerDao;
import net.atos.Codex_IOT.pojo.Customer;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao dao;
	
	public Customer findById(long id) {

		return dao.findById(id);
	}
	
	public List<Customer> findAll(){
		return dao.findAll();
	}
}
