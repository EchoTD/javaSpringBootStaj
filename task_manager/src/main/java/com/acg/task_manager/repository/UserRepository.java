package com.acg.task_manager.repository;

import java.util.Optional;
import com.acg.task_manager.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
