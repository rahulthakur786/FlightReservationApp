package com.search.flight_reservation_app_5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.flight_reservation_app_5.dto.ReservationRequest;
import com.search.flight_reservation_app_5.entity.FLIGHT;
import com.search.flight_reservation_app_5.entity.Passenger;
import com.search.flight_reservation_app_5.entity.Reservation;
import com.search.flight_reservation_app_5.repository.FlightRepository;
import com.search.flight_reservation_app_5.repository.PassengerRepository;
import com.search.flight_reservation_app_5.repository.ReservationRepository;
import com.search.flight_reservation_app_5.utilities.PDFgenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	

	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		String filePath = "F:\\tickets\\booking\\";
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		passengerRepo.save(passenger);
		
		long flightId = request.getFlightId();
		Optional<FLIGHT> findById = flightRepo.findById(flightId);
		FLIGHT flight = findById.get();
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		
		reservationRepo.save(reservation);
		
		PDFgenerator pdf = new PDFgenerator();
		pdf.generatePDF(filePath+reservation.getId()+".pdf", request.getFirstName(), request.getEmail(), request.getPhone(), flight.getOperatingAirlines(), flight.getDateOfDeparture(), flight.getDepartureCity(), flight.getArrivalCity());
		
		return reservation;
	}

}
