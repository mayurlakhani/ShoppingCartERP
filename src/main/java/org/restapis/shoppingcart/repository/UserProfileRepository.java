package org.restapis.shoppingcart.repository;


import org.restapis.shoppingcart.model.User;
import org.restapis.shoppingcart.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query(value = "SELECT * FROM user_profile  WHERE user_id = ?1", nativeQuery = true)
    UserProfile findUserByUserName(Long id);
}
