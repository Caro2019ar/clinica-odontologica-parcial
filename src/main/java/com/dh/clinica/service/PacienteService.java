package com.dh.clinica.service;


import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.impl.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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



    public Paciente actualizar(Paciente p) throws ResourceNotFoundException {
        Optional<Paciente> pacienteDB = this.pacienteRepository.findById(p.getId());

        if(pacienteDB.isPresent()){
            Paciente pacienteActualizado = pacienteDB.get();
                pacienteActualizado.setId(p.getId());
            if(p.getNombre()!=null){
            pacienteActualizado.setNombre(p.getNombre());
            }
            if(p.getApellido()!=null){
            pacienteActualizado.setApellido(p.getApellido());
            }
            if(p.getDni()!=null){
            pacienteActualizado.setDni(p.getDni());
            }
            if(p.getDomicilio()!=null){
            pacienteActualizado.getDomicilio().setId(p.getDomicilio().getId());
            }
            if(p.getDomicilio().getCalle()!=null){
            pacienteActualizado.getDomicilio().setCalle(p.getDomicilio().getCalle());
            }
            if(p.getDomicilio().getNumero()!=null){
            pacienteActualizado.getDomicilio().setNumero(p.getDomicilio().getNumero());
            }
            if(p.getDomicilio().getLocalidad()!=null){
            pacienteActualizado.getDomicilio().setLocalidad(p.getDomicilio().getLocalidad());
            }
            if(p.getDomicilio().getProvincia()!=null){
            pacienteActualizado.getDomicilio().setProvincia(p.getDomicilio().getProvincia());
            }
            pacienteRepository.save(pacienteActualizado);
            return pacienteActualizado;
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }
}
