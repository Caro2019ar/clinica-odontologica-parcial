package com.dh.clinica.controller;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.PacienteDTO;
import com.dh.clinica.service.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    Logger logger= LoggerFactory.getLogger(PacienteController.class);
    @Autowired
    private PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<?> registrarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.guardar(pacienteDTO));

    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Integer id) {
        PacienteDTO pacienteDTO = pacienteService.buscar(id);
        logger.info("Paciente buscado "+pacienteService.buscar(id));
        return ResponseEntity.ok(pacienteDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable("id") Integer id, @RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        pacienteDTO.setId(id);
        ResponseEntity<Paciente> response = null;
        if (id!= null && pacienteService.buscar(pacienteDTO.getId())!=null) {
            logger.info("Paciente actualizado: "+pacienteService.buscar(id));
            response = ResponseEntity.ok(pacienteService.actualizar(pacienteDTO));
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

        if (pacienteService.buscar(id)!=null) {
            pacienteService.eliminar(id);
            logger.info("Paciente eliminado "+pacienteService.buscar(id));
            response = ResponseEntity.ok("Paciente eliminado con exito.");
        } else {
            logger.error("Error: no se pudo eliminar el paciente");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

}
