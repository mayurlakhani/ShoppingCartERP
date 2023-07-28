package org.restapis.shoppingcart.repository;


import org.restapis.shoppingcart.model.User;
import org.restapis.shoppingcart.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
