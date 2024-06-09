package com.fastx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastx.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>{
	public Route findByRouteTo(String to);
}
