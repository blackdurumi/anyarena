package com.blackdurumi.anyarena.account.dao;

import com.blackdurumi.anyarena.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
