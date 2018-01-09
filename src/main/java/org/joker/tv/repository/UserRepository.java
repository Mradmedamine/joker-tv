package org.joker.tv.repository;

import org.joker.tv.model.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
    
}
