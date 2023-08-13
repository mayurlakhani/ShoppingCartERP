package org.restapis.shoppingcart.repository;

import org.restapis.shoppingcart.model.Transactions;
import org.restapis.shoppingcart.model.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaultRepository extends JpaRepository<Vault, Long> {


}
