package com.dh.clinica.service;


import com.dh.clinica.exception.NoEncontradoException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.impl.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardar(Paciente p) {
       return pacienteRepository.save(p);
    }

    public Optional<Paciente> buscar(Integer id) {
        return pacienteRepository.findById(Integer.valueOf(id));
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public void eliminar(Integer id) {
        pacienteRepository.deleteById(Integer.valueOf(id));
    }



    public Paciente actualizar(Paciente p) {
        Optional<Paciente> pacienteDB = this.pacienteRepository.findById(p.getId());
      if(pacienteDB.isPresent()){
            Paciente pacienteActualizado = pacienteDB.get();
            pacienteActualizado.setId(p.getId());
            pacienteActualizado.setNombre(p.getNombre());
            pacienteActualizado.setApellido(p.getApellido());
            pacienteActualizado.setDni(p.getDni());
            pacienteActualizado.setDomicilio(p.getDomicilio());
            pacienteActualizado.getDomicilio().setId(1);
            pacienteRepository.save(pacienteActualizado);
            return pacienteActualizado;
        } else {
        throw new NoEncontradoException("Paciente no encontrado");
        }
    }
}
