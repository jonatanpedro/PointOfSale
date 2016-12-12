package com.outrider.domain.repository;

import com.outrider.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jonatan on 10/12/2016.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserName(String userName);
}
