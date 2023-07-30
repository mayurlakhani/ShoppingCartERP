package org.restapis.shoppingcart.repository;

import org.restapis.shoppingcart.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Override
    Optional<Account> findById(Long aLong);

}
