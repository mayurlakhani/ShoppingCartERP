package org.restapis.shoppingcart.repository;

import org.restapis.shoppingcart.model.Account;
import org.restapis.shoppingcart.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {


}
