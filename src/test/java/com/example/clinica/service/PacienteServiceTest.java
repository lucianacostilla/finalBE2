package com.example.clinica.service;
import com.example.clinica.entities.Domicilio;
import com.example.clinica.entities.Paciente;
import com.example.clinica.repository.PacienteRepository;

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

//Hago estas inyecciones porque si no me daba error siempre. (ayuda de gpt)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AgregarPacienteTest {
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteRepository pacienteRepository;

    //creo domicilios y pacientes(mis coreanos favoritos)
    Domicilio domicilio1 = new Domicilio("Calle 1", 798, "Daegu", "Daegu");
    Domicilio domicilio2 = new Domicilio("Calle 2", 978, "Busan", "Busan");
    Domicilio domicilio9 = new Domicilio("Calle 3", 798, "Shanghái", "Jeolla del Sur");
    Domicilio domicilio4 = new Domicilio("Calle 5", 345, "Jeolla del Sur", "Gonggyeon-pa");
    Domicilio domicilio5 = new Domicilio("Calle 6", 678, "Seúl", "Gangwon");
    Domicilio domicilio6 = new Domicilio("Calle 7", 901, "Gyeonggi", "Gyeonggi");

    Domicilio domicilio8 = new Domicilio("Calle 9", 567, "Seúl", "Jeju");

    Paciente paciente1 = new Paciente("Baekhyun", "Byun", 12345678, LocalDate.now(), domicilio1);
    Paciente paciente2 = new Paciente("Jongin", "Kim", 87654321, LocalDate.of(1994, 1, 4), domicilio2);
    Paciente paciente3 = new Paciente("Kim", "Junmyeon", 76577654, LocalDate.of(1991, 5, 22), domicilio1);
    Paciente paciente4 = new Paciente("Kim", "Jongdae", 653765657, LocalDate.of(1992, 9, 21), domicilio4);
    Paciente paciente5 = new Paciente("Zhang", "Yixing", 6367765, LocalDate.of(1991, 10, 7), domicilio2);
    Paciente paciente6 = new Paciente("Park", "Chanyeol", 8678565, LocalDate.of(1992, 11, 27), domicilio5);
    Paciente paciente7 = new Paciente("Do", "Kyungsoo", 436543534, LocalDate.of(1993, 1, 12), domicilio6);
    Paciente paciente8 = new Paciente("Oh", "Sehun", 46745321, LocalDate.of(1994, 3, 26), domicilio8);
    Paciente paciente9 = new Paciente("Kim", "Minseok", 87654321, LocalDate.of(1990, 3, 26), domicilio9);




    @Test
    public void agregarPacienteTest() {
        //creo pacientes, me fijo si se crearon, los busco y analizo al final con comparaciones

        Paciente pacienteGuardado1 = pacienteService.agregarPaciente(paciente1);

        Paciente pacienteEncontrado1 = pacienteRepository.findById(paciente1.getId()).orElse(null);


        Paciente pacienteGuardado2 = pacienteService.agregarPaciente(paciente2);
        Paciente pacienteEncontrado2 = pacienteRepository.findById(paciente2.getId()).orElse(null);

        assertEquals(paciente1, pacienteGuardado1);
        assertEquals(paciente1, pacienteEncontrado1);

        assertEquals(paciente2, pacienteGuardado2);
        assertEquals(paciente2, pacienteEncontrado2);
    }

    @Test
    public void testFindAll() {
        //la parte de saveAll(Arrays.asList) tuve que buscarla en chatgpt porque si no no me salia de ninguna manera
        pacienteRepository.saveAll(Arrays.asList(paciente1, paciente2, paciente3, paciente4, paciente5, paciente6, paciente7, paciente8, paciente9));
        //creo una lista
        List<Paciente> pacientes = pacienteRepository.findAll();
        //deberia haber 9, asi que comparo.
        assertEquals(9, pacientes.size());
        assertEquals("Baekhyun", pacientes.get(0).getNombre());
        assertEquals("Byun", pacientes.get(0).getApellido());
        assertEquals(12345678, pacientes.get(0).getDni());
        assertEquals(LocalDate.now(), pacientes.get(0).getFechaDeAlta());
        assertEquals("Jongin", pacientes.get(1).getNombre());
        assertEquals("Kim", pacientes.get(1).getApellido());
        assertEquals(87654321, pacientes.get(1).getDni());
        assertEquals(LocalDate.of(1994, 1, 4), pacientes.get(1).getFechaDeAlta());
    }



    @Test
    public void eliminarPaciente() {
        //creo un paciente, lo borro e intento buscarlo. Pero como lo borré, me da null. Por eso assertNull.

        pacienteService.agregarPaciente(paciente1);
        pacienteService.eliminarPaciente(paciente1);
        Paciente pacienteBorrado1 = pacienteRepository.findById(paciente1.getId()).orElse(null);
        assertNull(pacienteBorrado1);
    }

}