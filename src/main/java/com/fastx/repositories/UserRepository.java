package com.fastx.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fastx.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByMobileNumber(String number);
	//public List<User> findByBookingListIsNotEmpty();

	public Optional<User> findByUserName(String username);

	public boolean existsByUserName(String userName);

	public boolean existsByEmail(String email);
	
}