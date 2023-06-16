package com.search.flight_reservation_app_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.search.flight_reservation_app_5.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
