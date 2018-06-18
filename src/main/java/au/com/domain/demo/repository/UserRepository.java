package au.com.domain.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.domain.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findById(Long id);
}