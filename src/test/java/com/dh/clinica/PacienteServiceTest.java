package com.dh.clinica;

import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.PacienteDTO;
import com.dh.clinica.service.DomicilioService;
import com.dh.clinica.service.PacienteService;

import org.junit.Assert;


import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Set;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DomicilioService domicilioService;

    public void cargarDataSet() {
        Paciente p =this.pacienteService.guardar(new PacienteDTO("Santiago", "Paz", "88888888",new Domicilio("Av " +
                "Santa fe", "444", "CABA", "Buenos Aires")));

    }

    @Test
    public void agregarYBuscarPacienteTest() {
        Paciente p = pacienteService.guardar(new PacienteDTO("Tomas","Silva","77.789.456-00",new Domicilio("Av " +
                "Avellaneda", "333", "CABA", "Buenos Aires")));

        Assert.assertNotNull(pacienteService.buscar(p.getId()));
    }

    @Test
    public void buscarPacientePorDni() throws ResourceNotFoundException {
        Paciente p = pacienteService.guardar(new PacienteDTO("Tomas","Silva","77.789.456-00",new Domicilio("Av " +
                "Avellaneda", "333", "CABA", "Buenos Aires")));

        Assert.assertNotNull(pacienteService.buscarPacienteInformandoDni("77.789.456-00"));
    }

    @Test
    public void eliminarPacienteTest() {
        pacienteService.eliminar(1);
        Assert.assertTrue(pacienteService.buscar(1)==null);

    }

    @Test
    public void traerTodos() {
        this.cargarDataSet();
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
    }


}

