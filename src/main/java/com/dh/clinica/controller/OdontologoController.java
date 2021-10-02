package com.dh.clinica.controller;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;

import com.dh.clinica.model.OdontologoDTO;
import com.dh.clinica.service.OdontologoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    Logger logger= LoggerFactory.getLogger(OdontologoController.class);

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping()
    public ResponseEntity<?> registrarOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologoDTO));

    }

    @GetMapping("/{id}")
    public OdontologoDTO buscar(@PathVariable Integer id) {
        logger.info("Odontologo buscado "+odontologoService.buscar(id));
        return odontologoService.buscar(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable("id") Integer id,
                                                 @RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        odontologoDTO.setId(id);
        return  ResponseEntity.ok(odontologoService.actualizar(odontologoDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException{
        ResponseEntity<String> response = null;
        if (odontologoService.buscar(id)!=null) {
            odontologoService.eliminar(id);
            logger.info("Odontologo eliminado "+odontologoService.buscar(id));
            response = ResponseEntity.ok("Odontologo eliminado con exito.");
        } else {
            throw new ResourceNotFoundException("No existe odontologo con id: "+id);
        }
        return response;
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }



}
