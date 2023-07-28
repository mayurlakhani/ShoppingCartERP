package org.restapis.shoppingcart.repository;


import org.restapis.shoppingcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query(
            value = "SELECT * FROM USER u WHERE u.username = ?1",
            nativeQuery = true)
    List<User> findByUserNameEquals(String username);
}
