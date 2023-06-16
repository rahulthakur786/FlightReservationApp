package com.search.flight_reservation_app_5.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.search.flight_reservation_app_5.entity.FLIGHT;

public interface FlightRepository extends JpaRepository<FLIGHT, Long> {

	@Query("from FLIGHT where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
	List<FLIGHT> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to, @Param("dateOfDeparture") Date departureDate);

}
