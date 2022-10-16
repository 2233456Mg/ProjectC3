package com.ciclo3.projectc3.Controller;

import com.ciclo3.projectc3.Entities.Reservation;
import com.ciclo3.projectc3.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getReservations() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationService.getReservation(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/update")
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public boolean deleteReservation(@PathVariable("id") int id) {
        return reservationService.deleteReservation(id);
    }
}