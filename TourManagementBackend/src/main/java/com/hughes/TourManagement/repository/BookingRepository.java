package com.hughes.TourManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hughes.TourManagement.model.Booking;
import com.hughes.TourManagement.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	public List<Booking> findByTourid(int id);
}
