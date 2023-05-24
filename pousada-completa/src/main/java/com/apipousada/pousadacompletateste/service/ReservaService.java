package com.apipousada.pousadacompletateste.service;

import org.springframework.stereotype.Service;

import com.apipousada.pousadacompletateste.model.ReservaModel;
import com.apipousada.pousadacompletateste.repositories.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<ReservaModel> getAllReservas() {
        return reservaRepository.findAll();
    }

    public ReservaModel getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public ReservaModel createReserva(ReservaModel reserva) {
        return reservaRepository.save(reserva);
    }

    public ReservaModel updateReserva(Long id, ReservaModel reserva) {
        ReservaModel existingReserva = reservaRepository.findById(id).orElse(null);
        if (existingReserva != null) {
            existingReserva.setDataInicio(reserva.getDataInicio());
            existingReserva.setDataFim(reserva.getDataFim());
            existingReserva.setPessoa(reserva.getPessoa());
            existingReserva.setQuarto(reserva.getQuarto());
            return reservaRepository.save(existingReserva);
        } else {
            return null;
        }
    }

    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<ReservaModel> getReservasByPessoaId(Long pessoaId) {
        return reservaRepository.findByPessoaId(pessoaId);
    }
    
}

