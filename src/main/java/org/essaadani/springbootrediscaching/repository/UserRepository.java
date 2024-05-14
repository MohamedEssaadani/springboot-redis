package org.essaadani.springbootrediscaching.repository;

import org.essaadani.springbootrediscaching.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
