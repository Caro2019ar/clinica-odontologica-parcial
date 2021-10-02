package com.dh.clinica.service;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Turno;
import com.dh.clinica.repository.impl.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno registrarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public List<Turno> listar(){
        return turnoRepository.findAll();
    }
    public void eliminar(Integer id){
        turnoRepository.deleteById(Integer.valueOf(id));
    }

    public Turno actualizar(Turno turno) throws ResourceNotFoundException{
        Optional<Turno> turnoDB=this.turnoRepository.findById(turno.getId());
        Turno turnoActual = turnoDB.get();
        if(turnoDB.isPresent()) {
            turnoActual.setId(turno.getId());
            turnoActual.setOdontologo(turno.getOdontologo());
            turnoActual.getOdontologo().setId(turno.getOdontologo().getId());
            turnoActual.setPaciente(turno.getPaciente());
            turnoActual.getPaciente().setId(turno.getPaciente().getId());
            turnoActual.setDate(turno.getDate());
            turnoRepository.save(turnoActual);
            return turnoActual;
        } else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }


    public Optional<Turno> buscar(Integer id){
        return turnoRepository.findById(Integer.valueOf(id));
    }

}
