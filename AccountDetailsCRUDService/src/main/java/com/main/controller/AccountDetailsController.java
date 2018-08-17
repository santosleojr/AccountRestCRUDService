package com.main.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.main.model.AccountDetails;
import com.main.services.AccountDetailsService;

@RestController
public class AccountDetailsController {
	
	@Autowired
	private AccountDetailsService adtlsService;

	@GetMapping(value="/Account/{AccId}")
	public ResponseEntity<Optional<AccountDetails>> getAccountDetails(@PathVariable String AccId){
		Optional<AccountDetails> optadtls = adtlsService.getSingleAccountDetails(AccId);
		if(!optadtls.isPresent()) return new ResponseEntity<Optional<AccountDetails>>(optadtls, HttpStatus.NO_CONTENT);
		return new ResponseEntity<Optional<AccountDetails>>(optadtls, HttpStatus.FOUND);
	}
	
	@GetMapping(value="/Account")
	public ResponseEntity<ArrayList<AccountDetails>> getAllAccountDetails(){
		return new ResponseEntity<ArrayList<AccountDetails>>(adtlsService.getAllAccountDetails(), HttpStatus.OK);
	}
	
	@PostMapping(value="/Account")
	public ResponseEntity<Void> createAccountDetails(@RequestBody AccountDetails adetails, UriComponentsBuilder uri){
		//If record already exists, service is to return conflict response. User should use Put method for already existing records.
		if(!adtlsService.createAccountDetails(adetails)) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders http = new HttpHeaders();
		http.setLocation(uri.path("/Account/"+adetails.getAccountId()).build().toUri());
		return new ResponseEntity<Void>(http, HttpStatus.CREATED);
	}
	 
	@PutMapping(value="/Account/{AccId}")
	public ResponseEntity<Void> updateAccountDetails(@RequestBody AccountDetails adetails, @PathVariable String AccId){
		//If the Id in the URI is different from Id provided in the JSON, Id in the URI will be used
		adetails.setAccountId(AccId);
		adtlsService.updateAccountDetails(adetails);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/Account/{AccId}")
	public ResponseEntity<Void> deleteAccountDetails(@PathVariable String AccId){
		adtlsService.deleteAccountDetails(AccId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
