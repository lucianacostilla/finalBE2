package com.example.clinica.service;
import com.example.clinica.entities.Odontologo;
import com.example.clinica.repository.OdontologoRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private OdontologoRepository odontologoRepository;

    Odontologo odontologo1 = new Odontologo("Chanyeol", "Park", 365743);
    Odontologo odontologo2 = new Odontologo("Jongdae", "Kim", 546643);
    Odontologo odontologo3 = new Odontologo("Kibum", "Kim", 234054);


    @Test
    void agregarOdontologo() {
       Odontologo odontologoGuardado1 = odontologoService.guardarOdontologo(odontologo1);

        Odontologo odontologoEncontrado1 = odontologoRepository.findById(odontologo1.getId()).orElse(null);


        Odontologo odontologoGuardado2 = odontologoService.guardarOdontologo(odontologo2);
        Odontologo odontologoEncontrado2 = odontologoRepository.findById(odontologo2.getId()).orElse(null);

        assertEquals(odontologo1, odontologoGuardado1);
        assertEquals(odontologo1, odontologoEncontrado1);

        assertEquals(odontologo2, odontologoGuardado2);
        assertEquals(odontologo2, odontologoEncontrado2);
    }

    @Test
    void listarOdontologos() { odontologoRepository.saveAll(Arrays.asList(odontologo1, odontologo2, odontologo3));
        //creo una lista
        List<Odontologo> odontologos = odontologoRepository.findAll();
        //deberia haber 9, asi que comparo.
        assertEquals(3, odontologos.size());
        assertEquals("Chanyeol", odontologos.get(0).getNombre());
        assertEquals("Park", odontologos.get(0).getApellido());
        assertEquals(365743, odontologos.get(0).getNumeroDeMatricula());
        assertEquals("Jongdae", odontologos.get(1).getNombre());
        assertEquals("Kim", odontologos.get(1).getApellido());
        assertEquals(546643,odontologos.get(1).getNumeroDeMatricula());
    }


    @Test
    void eliminarOdontologo() {
        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.eliminarOdontologoPorId(odontologo1.getId());
        Odontologo odontologBorrado1 = odontologoRepository.findById(odontologo1.getId()).orElse(null);
        assertNull(odontologBorrado1);
    }


}