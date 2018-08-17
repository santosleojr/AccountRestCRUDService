package com.main.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.AccountDetailsDAO;
import com.main.model.AccountDetails;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {
	
	@Autowired
	private AccountDetailsDAO adDao;

	public Optional<AccountDetails> getSingleAccountDetails(String accountId){
		return adDao.findById(accountId);
	}
	
	public ArrayList<AccountDetails> getAllAccountDetails(){
		ArrayList<AccountDetails> adtlsList = new ArrayList<AccountDetails>();
		Iterable<AccountDetails> iter = adDao.findAll();
		Iterator<AccountDetails> it = iter.iterator();
		while(it.hasNext())
		{
			adtlsList.add((AccountDetails)it.next());
		}
		return adtlsList;
	}
	
	public synchronized boolean createAccountDetails(AccountDetails adtls){
		if(adDao.findById(adtls.getAccountId()).isPresent()) return false;
		adDao.save(adtls);
		return true;
	}
	
	public void updateAccountDetails(AccountDetails adtls){
		adDao.save(adtls);
	}
	
	public void deleteAccountDetails(String accountId){
		adDao.deleteById(accountId);
	}
}
