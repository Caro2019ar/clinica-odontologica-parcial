package com.dh.clinica.service;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.OdontologoDTO;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.util.Jsons;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

     @Autowired
    ObjectMapper mapper;

    public Odontologo registrarOdontologo(OdontologoDTO odontologoDTO) {
         Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        return odontologoRepository.save(odontologo);
    }

    public void eliminar(Integer id) {
        odontologoRepository.deleteById(Integer.valueOf(id));
    }

    public OdontologoDTO buscar(Integer id) {
        OdontologoDTO odontologoDTO = null;
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            odontologoDTO=mapper.convertValue(odontologo, OdontologoDTO.class);
        }
        return odontologoDTO;
    }
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }


/*
    public void actualizarQuery(Integer id, String nombre) {
        odontologoRepository.actualizarQuery(id, nombre);
    }
*/

   /* public Odontologo actualizar(OdontologoDTO odontologoDTO) throws ResourceNotFoundException{
         Odontologo odontologo=mapper.convertValue(odontologoDTO,Odontologo.class);
        odontologoRepository.save(odontologo);
        return odontologo;
    }*/

    /*public Odontologo actualizar(Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoDB= this.odontologoRepository.findById(odontologo.getId());
        if(odontologoDB.isPresent()){
            Odontologo odontoActual = odontologoDB.get();
            odontoActual.setId(odontologo.getId());
            odontoActual.setNombre(odontologo.getNombre());
            odontoActual.setApellido(odontologo.getApellido());
            odontoActual.setMatricula(odontologo.getMatricula());
            odontologoRepository.save(odontoActual);
            return odontoActual;
        }
        else {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }

    }*/

    public Odontologo actualizar(OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoDB= this.odontologoRepository.findById(odontologoDTO.getId());
        //System.out.println("JSONS------"+Jsons.asJsonString(odontologoDTO));
        if(odontologoDB.isPresent()){
            Odontologo odontoActual = odontologoDB.get();
            odontoActual.setId(odontologoDTO.getId());
            if(odontologoDTO.getNombre()!=null){
                odontoActual.setNombre(odontologoDTO.getNombre());
            }
            if(odontologoDTO.getApellido()!=null){
            odontoActual.setApellido(odontologoDTO.getApellido());
            }
            if(odontologoDTO.getMatricula()!=null){
            odontoActual.setMatricula(odontologoDTO.getMatricula());
            }
            odontologoRepository.save(odontoActual);
            return odontoActual;
        }
        else {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }

    }

}
