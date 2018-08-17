package com.main.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.model.AccountDetails;

@Repository
public interface AccountDetailsDAO extends CrudRepository<AccountDetails, String> {

}
