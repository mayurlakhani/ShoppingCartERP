package org.restapis.shoppingcart.repository;


import org.restapis.shoppingcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query(
            value = "SELECT * FROM USER u WHERE u.username = ?1",
            nativeQuery = true)
    List<User> findByUserNameEquals(String username);


    User findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByEmail(String email);
}
