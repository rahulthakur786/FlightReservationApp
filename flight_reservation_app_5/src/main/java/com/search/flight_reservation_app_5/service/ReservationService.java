package com.search.flight_reservation_app_5.service;

import com.search.flight_reservation_app_5.dto.ReservationRequest;
import com.search.flight_reservation_app_5.entity.Reservation;

public interface ReservationService {
	Reservation bookFlight(ReservationRequest request);
}
