package com.main.services;

import java.util.ArrayList;
import java.util.Optional;

import com.main.model.AccountDetails;

public interface AccountDetailsService {

	Optional<AccountDetails> getSingleAccountDetails(String accId);

	ArrayList<AccountDetails> getAllAccountDetails();

	boolean createAccountDetails(AccountDetails adetails);

	void updateAccountDetails(AccountDetails adetails);

	void deleteAccountDetails(String accId);

}
