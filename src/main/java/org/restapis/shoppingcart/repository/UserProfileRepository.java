package org.restapis.shoppingcart.repository;


import org.restapis.shoppingcart.dto.AccountDto;
import org.restapis.shoppingcart.model.User;
import org.restapis.shoppingcart.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

     Page<UserProfile> findByUserId(Long userId, Pageable pageable);


    @Query(value = "SELECT * FROM user_profile  WHERE user_id = ?1", nativeQuery = true)
    UserProfile findUserByUserId(Long id);

    UserProfile findByIdAndUserId(Long userId, Long id);

    Page<UserProfile> findByAccountId(long accountId, Pageable pageable);

    Optional<UserProfile> findByAccountId(long id);
}
