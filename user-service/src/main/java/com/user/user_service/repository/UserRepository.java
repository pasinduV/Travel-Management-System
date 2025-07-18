package com.user.user_service.repository;

import com.user.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    Optional<User> findByUsername(String username);

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if username exists
    Boolean existsByUsername(String username);

    // Check if email exists
    Boolean existsByEmail(String email);

    // Find users by username containing (case-insensitive)
    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))")
    List<User> findByUsernameContainingIgnoreCase(@Param("username") String username);

    // Find users by email containing (case-insensitive)
    @Query("SELECT u FROM User u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<User> findByEmailContainingIgnoreCase(@Param("email") String email);

    // Find users created after a specific date
    @Query("SELECT u FROM User u WHERE u.createdAt >= :createdAt ORDER BY u.createdAt DESC")
    List<User> findUsersCreatedAfter(@Param("createdAt") java.time.LocalDateTime createdAt);
}
