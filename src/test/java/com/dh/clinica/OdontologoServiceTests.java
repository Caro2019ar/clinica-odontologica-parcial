package com.dh.clinica;

import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.OdontologoDTO;
import com.dh.clinica.service.OdontologoService;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {
    @Autowired
    private OdontologoService odontologoService;

    @BeforeEach
    public void cargarDataSet() {
        odontologoService.registrarOdontologo(new OdontologoDTO("Santiago", "Paz", 3455647));
    }

    @Test
    public void agregarOdontologo() {
        Odontologo odontologo = odontologoService.registrarOdontologo(new OdontologoDTO("Juan", "Ramirez", 348971960));
        Assert.assertTrue(odontologo.getId() != null);

    }

    @Test
    public void eliminarOdontologoTest() {
        odontologoService.eliminar(1);
        Assert.assertFalse(odontologoService.buscar(1)!=null);

    }

    @Test
    public void traerTodos() {
        this.cargarDataSet();
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
    }

}
