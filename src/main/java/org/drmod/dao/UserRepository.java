package org.drmod.dao;

import org.drmod.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created on 7/6/2015
 *
 * @author drmod
 */
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByFirstName(String firstName);
}
