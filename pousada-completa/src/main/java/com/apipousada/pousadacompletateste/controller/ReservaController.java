package com.apipousada.pousadacompletateste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apipousada.pousadacompletateste.model.ReservaModel;
import com.apipousada.pousadacompletateste.service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/pousada/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaModel>> getAllReservas() {
        List<ReservaModel> reservas = reservaService.getAllReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> getReservaById(@PathVariable Long id) {
        ReservaModel reserva = reservaService.getReservaById(id);
        if (reserva != null) {
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ReservaModel> createReserva(@RequestBody ReservaModel reserva) {
        ReservaModel createdReserva = reservaService.createReserva(reserva);
        return new ResponseEntity<>(createdReserva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaModel> updateReserva(@PathVariable Long id, @RequestBody ReservaModel reserva) {
        ReservaModel updatedReserva = reservaService.updateReserva(id, reserva);
        if (updatedReserva != null) {
            return new ResponseEntity<>(updatedReserva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
