package com.blackdurumi.anyarena.account.dao;

import com.blackdurumi.anyarena.account.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
