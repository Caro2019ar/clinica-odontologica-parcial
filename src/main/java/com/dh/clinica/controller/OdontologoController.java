package com.dh.clinica.controller;

import com.dh.clinica.model.Odontologo;

import com.dh.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping()
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable Integer id) {
        Odontologo odontologo = odontologoService.buscar(id).orElse(null);
        return ResponseEntity.ok(odontologo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable("id") Integer id, @RequestBody Odontologo odontologo) {
        odontologo.setId(id);
        return  ResponseEntity.ok(odontologoService.actualizar(odontologo));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if (odontologoService.buscar(id).isPresent()) {
            odontologoService.eliminar(id);
            response = ResponseEntity.ok("Odontologo eliminado con exito.");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }



}
