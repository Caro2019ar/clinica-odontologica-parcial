package com.dh.clinica.controller;

import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    Logger logger= LoggerFactory.getLogger(PacienteController.class);
    @Autowired
    private PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id) {
        Paciente paciente = pacienteService.buscar(id).orElse(null);
        logger.info("Paciente buscado "+pacienteService.buscar(id));
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable("id") Integer id, @RequestBody Paciente paciente) {
        paciente.setId(id);
        ResponseEntity<Paciente> response = null;
        if (id!= null && pacienteService.buscar(paciente.getId()).isPresent()) {
            logger.info("Paciente actualizado: "+pacienteService.buscar(id));
            response = ResponseEntity.ok(pacienteService.actualizar(paciente));
        }
        else {
            logger.error("Error: no se pudo actualizar el paciente");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (pacienteService.buscar(id).isPresent()) {
            pacienteService.eliminar(id);
            logger.info("Paciente eliminado "+pacienteService.buscar(id));
            response = ResponseEntity.ok("Paciente eliminado con exito.");
        } else {
            logger.error("Error: no se pudo eliminar el paciente");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}
