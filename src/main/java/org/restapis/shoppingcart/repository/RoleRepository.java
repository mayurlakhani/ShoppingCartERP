package org.restapis.shoppingcart.repository;



import org.restapis.shoppingcart.enumerations.ERole;
import org.restapis.shoppingcart.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}