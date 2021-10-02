package com.dh.clinica;


import com.dh.clinica.model.*;

import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    public void cargarDataSet() {
        Paciente paciente = this.pacienteService.guardar(new PacienteDTO("Santiago", "Paz", "88888888", new Domicilio(
                "Av " +
                        "Santa fe", "444", "CABA", "Buenos Aires")));
        Odontologo odontologo = this.odontologoService.registrarOdontologo(new OdontologoDTO("Santiago", "Paz",
                3455647));
        Date date = new Date();
        this.turnoService.registrarTurno(new Turno(paciente,odontologo,new Date()));
    }

    @Test
    public void cargarTurno(){
        Assert.assertNotNull(turnoService.buscar(1));
    }

    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoService.buscar(1));
    }

    @Test
    public void eliminarTurnoTest(){
        this.cargarDataSet();
        turnoService.eliminar(1);
        Assert.assertFalse(turnoService.buscar(1).isPresent());
    }

}

