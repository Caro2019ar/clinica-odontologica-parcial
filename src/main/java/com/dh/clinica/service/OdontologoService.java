package com.dh.clinica.service;

import com.dh.clinica.exception.NoEncontradoException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.repository.impl.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);

    }

    public void eliminar(Integer id) {
        odontologoRepository.deleteById(Integer.valueOf(id));
    }

    public Optional<Odontologo> buscar(Integer id) {
        return odontologoRepository.findById(Integer.valueOf(id));
    }

    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo) {
        Optional<Odontologo> odontologoDB= this.odontologoRepository.findById(odontologo.getId());
        if(odontologoDB.isPresent()){
            Odontologo odontoActual = odontologoDB.get();
            odontoActual.setId(odontologo.getId());
            odontoActual.setNombre(odontologo.getNombre());
            odontoActual.setApellido(odontologo.getApellido());
            odontoActual.setMatricula(odontologo.getMatricula());
            odontologoRepository.save(odontoActual);
            return odontoActual;
        } else {
            throw new NoEncontradoException("Odontologo no encontrado");
        }

    }
}
