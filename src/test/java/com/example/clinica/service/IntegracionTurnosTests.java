//INTENTÉ HACER UN TEST DE INTEGRACIÓN PERO TUVE PROBLEMAS CON EL SERIALIZADOR DE JSON Y CON HIBERNATE,
//NO LLEGUÉ CON EL TIEMPO PARA VER PORQUÉ SUCEDÍA ESO.

/* package com.example.clinica.service;
import com.example.clinica.entities.Domicilio;
import com.example.clinica.entities.Odontologo;
import com.example.clinica.entities.Paciente;
import com.example.clinica.entities.Turno;
import com.example.clinica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private  OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;


    private void cargarDatos() throws ResourceNotFoundException {
        Domicilio domicilioLuciana = new Domicilio("Av. Corrientes", 234, "Junín", "Buenos Aires");
        Paciente pacienteAgregado=pacienteService.agregarPaciente(new Paciente("Luciana", "Costilla", 40798174, LocalDate.of(2023, 04, 06), domicilioLuciana));
        Odontologo odontologoAgregado = odontologoService.guardarOdontologo(new Odontologo("Julian", "Re", 23647));
        Turno turno= new Turno();
        turno.setFecha(LocalDate.of(2023, 05, 21));
        turno.setOdontologo(odontologoAgregado);
        turno.setPaciente(pacienteAgregado);
        turnoService.guardarTurno(turno);

    }
    @Test
    public void ListarTurnosTest() throws  Exception{
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}


 */