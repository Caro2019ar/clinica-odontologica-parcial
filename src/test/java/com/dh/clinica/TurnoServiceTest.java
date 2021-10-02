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
    public void cargarDataTurno() {
        Turno turno = new Turno();
        turno.setOdontologo(new Odontologo("Santiago", "Paz",
                3455647));
        turno.setPaciente(new Paciente("Santiago", "Paz", "88888888", new Domicilio(
                "Av " +
                        "Santa fe", "444", "CABA", "Buenos Aires")));
        turno.setDate(new Date());
        turno.setId(1);
        this.turnoService.registrarTurno(turno);

    }

    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoService.buscar(1));
    }

    @Test
    public void eliminarTurnoTest(){
        this.cargarDataTurno();
        turnoService.eliminar(1);
        Assert.assertTrue(turnoService.buscar(1)==null);
    }

}

