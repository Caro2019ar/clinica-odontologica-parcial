package com.dh.clinica.controller;

import com.dh.clinica.model.Turno;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    Logger logger= LoggerFactory.getLogger(TurnoController.class);
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<String> registrarTurno(@RequestBody Turno turno) {
        if (pacienteService.buscar(turno.getPaciente().getId()).isPresent() && odontologoService.buscar(turno.getOdontologo().getId())!=null) {
            turnoService.registrarTurno(turno);
            return ResponseEntity.ok("Turno registrado con exito.");
        } else {
            logger.error("Error: no se pudo registrar el turno");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response;
        if (turnoService.buscar(id).isPresent()) {
            turnoService.eliminar(id);
            response = ResponseEntity.ok("Turno eliminado con exito.");
        } else {
            logger.error("Error: no se pudo eliminar el turno");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTurno(@PathVariable("id") Integer id, @RequestBody Turno turno) {
        if (turnoService.buscar(id).isPresent()) {
            turno.setId(id);
            turnoService.actualizar(turno);
            return ResponseEntity.ok("Turno actualizado con exito.");
        } else {
            logger.error("Error: no se pudo actualizar el turno");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
