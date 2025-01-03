package br.com.airb.room.service;

import br.com.airb.room.model.Publicity;
import br.com.airb.room.model.Reservations;
import br.com.airb.room.model.dto.RequestReservationDto;
import br.com.airb.room.model.dto.ResponseReservationDto;
import br.com.airb.room.model.dto.ResponseReservationWithPublicityDto;
import br.com.airb.room.repository.PublicityRepository;
import br.com.airb.room.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PublicityRepository publicityRepository;

    public ResponseReservationDto createReservation(RequestReservationDto requestReservationDto, Long publicityId) {
        if (isDateOccupied(publicityId, requestReservationDto.checkin(), requestReservationDto.checkout())) {
            throw new IllegalArgumentException("As datas selecionadas já estão ocupadas.");
        }

        long calculatingNumberOfDays = calculatingNumberOfDays(requestReservationDto.checkin(), requestReservationDto.checkout());
        BigDecimal calculatingTotalValue = requestReservationDto.dailyValue()
                .multiply(new BigDecimal(calculatingNumberOfDays));

        Publicity publicity = publicityRepository.findById(publicityId)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        Reservations returnReservation = reservationRepository.save(
                toReservationDto(requestReservationDto, calculatingTotalValue, publicity));

        return toConverteReservationparaResponseReservationDto(returnReservation);
    }


    public boolean isDateOccupied(Long publicityId, Date checkin, Date checkout) {
        List<Reservations> reservations = reservationRepository.findAll();

        for (Reservations reservation : reservations) {
            if (reservation.getPublicity().getId().equals(publicityId) &&
                    reservation.getCheckin().before(checkout) &&
                    reservation.getCheckout().after(checkin)) {
                return true;
            }
        }
        return false;
    }


    private long calculatingNumberOfDays(Date checkin, Date checkout) {
        long diffInMillies = checkout.getTime() - checkin.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private ResponseReservationDto toConverteReservationparaResponseReservationDto(Reservations reservation) {
        return new ResponseReservationDto(
                reservation.getId(),
                reservation.getCheckin(),
                reservation.getCheckout(),
                reservation.getDailyValue(),
                reservation.getTotalValue(),
                reservation.getPaymentMethod(),
                reservation.getAdvancePayment()
        );
    }

    private Reservations toReservationDto(RequestReservationDto requestReservationDto, BigDecimal calculatingTotalValue, Publicity publicity) {
        Reservations reservations = new Reservations();
        reservations.setCheckin(requestReservationDto.checkin());
        reservations.setCheckout(requestReservationDto.checkout());
        reservations.setDailyValue(requestReservationDto.dailyValue());
        reservations.setTotalValue(calculatingTotalValue);
        reservations.setPaymentMethod(requestReservationDto.paymentMethod());
        reservations.setAdvancePayment(requestReservationDto.advancePayment());
        reservations.setPublicity(publicity);
        return reservations;
    }

    public List<ResponseReservationDto> getAllReservations() {
        List<Reservations> reservations =
                reservationRepository.findAll();
        return reservations.stream()
                .map(this::toConverteReservationparaResponseReservationDto)
                .collect(Collectors.toList());
    }

    public ResponseReservationDto getReservationById(Long id) {
        Reservations reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        return toConverteReservationparaResponseReservationDto(reservation);
    }


    public ResponseReservationDto updateReservation(Long id, RequestReservationDto requestReservationDto) {
        Reservations reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        long calculatingNumberOfDays = calculatingNumberOfDays(requestReservationDto.checkin(), requestReservationDto.checkout());
        BigDecimal calculatingTotalValue = requestReservationDto.dailyValue().multiply(new BigDecimal(calculatingNumberOfDays));

        reservation.setCheckin(requestReservationDto.checkin());
        reservation.setCheckout(requestReservationDto.checkout());
        reservation.setDailyValue(requestReservationDto.dailyValue());
        reservation.setTotalValue(calculatingTotalValue);
        reservation.setPaymentMethod(requestReservationDto.paymentMethod());
        reservation.setAdvancePayment(requestReservationDto.advancePayment());

        reservationRepository.save(reservation);

        return toConverteReservationparaResponseReservationDto(reservation);
    }

    public void deleteReservation(Long id) {
        Reservations reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));

        reservationRepository.delete(reservation);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {

        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public List<ResponseReservationWithPublicityDto> getAllReservationsByPublicityId(Long publicityId) {
        Publicity publicity = publicityRepository.findById(publicityId)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        List<Reservations> reservations = reservationRepository.findByPublicity(publicity);

        return reservations.stream()
                .map(reservation -> new ResponseReservationWithPublicityDto(
                        toConverteReservationparaResponseReservationDto(reservation),
                        new ResponseReservationWithPublicityDto.PublicityDto(publicity)))
                .collect(Collectors.toList());
    }


}

