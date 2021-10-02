package com.dh.clinica.service;


import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.PacienteDTO;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService {
    @Autowired
    ObjectMapper mapper;

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardar(PacienteDTO pacienteDTO) {
        Paciente paciente =mapper.convertValue(pacienteDTO, Paciente.class);
        return pacienteRepository.save(paciente);
    }

    public PacienteDTO buscar(Integer id) {
        Optional<Paciente> paciente= pacienteRepository.findById(Integer.valueOf(id));
        PacienteDTO pacienteDTO = mapper.convertValue(paciente,PacienteDTO.class);
        return pacienteDTO;
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }


    public Set<PacienteDTO> buscarPacienteInformandoDni(String dni) {
        Set<Paciente> pacientes = pacienteRepository.buscarPacientePorDNI(dni);

        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        for(Paciente paciente : pacientes){
            PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
            pacientesDTO.add(pacienteDTO);
        }
        return pacientesDTO;

    }


    public void eliminar(Integer id) {
        pacienteRepository.deleteById(Integer.valueOf(id));
    }



    public Paciente actualizar(PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        Optional<Paciente> pacienteDB = this.pacienteRepository.findById(pacienteDTO.getId());

        if(pacienteDB.isPresent()){
            Paciente pacienteActualizado = pacienteDB.get();
                pacienteActualizado.setId(pacienteDTO.getId());
            if(pacienteDTO.getNombre()!=null){
            pacienteActualizado.setNombre(pacienteDTO.getNombre());
            }
            if(pacienteDTO.getApellido()!=null){
            pacienteActualizado.setApellido(pacienteDTO.getApellido());
            }
            if(pacienteDTO.getDni()!=null){
            pacienteActualizado.setDni(pacienteDTO.getDni());
            }
            if(pacienteDTO.getDomicilio()!=null){
            pacienteActualizado.getDomicilio().setId(pacienteDTO.getDomicilio().getId());
            }
            if(pacienteDTO.getDomicilio().getCalle()!=null){
            pacienteActualizado.getDomicilio().setCalle(pacienteDTO.getDomicilio().getCalle());
            }
            if(pacienteDTO.getDomicilio().getNumero()!=null){
            pacienteActualizado.getDomicilio().setNumero(pacienteDTO.getDomicilio().getNumero());
            }
            if(pacienteDTO.getDomicilio().getLocalidad()!=null){
            pacienteActualizado.getDomicilio().setLocalidad(pacienteDTO.getDomicilio().getLocalidad());
            }
            if(pacienteDTO.getDomicilio().getProvincia()!=null){
            pacienteActualizado.getDomicilio().setProvincia(pacienteDTO.getDomicilio().getProvincia());
            }
            pacienteRepository.save(pacienteActualizado);
            return pacienteActualizado;
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }
}
