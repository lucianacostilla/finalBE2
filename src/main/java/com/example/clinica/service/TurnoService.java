package com.example.clinica.service;
import com.example.clinica.entities.Turno;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.repository.TurnoRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger logger = LogManager.getLogger(TurnoService.class);

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;


    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    public Turno guardarTurno(Turno turno) throws ResourceNotFoundException {
        if(turno.getPaciente() == null || turno.getOdontologo() == null){
            throw new ResourceNotFoundException("Odontologo o paciente vacío. Intente de nuevo");
        }
        logger.info("Guardando turno");
        return turnoRepository.save(turno);
    }


    public void eliminarTurnoPorId(Long id){
        logger.info("Eliminando turno con id: " + id);
        turnoRepository.deleteById(id);
    }

    public List<Turno> obtenerTurnos(){
        logger.info("Obteniendo todos los turnos");
        return turnoRepository.findAll();
    }


    public Optional<Turno> obtenerTurnoPorId(Long id){
        logger.info("Obteniendo turno con id: " + id);
        return turnoRepository.findById(id);
    }

    public Turno actualizarTurno(Long id, Turno turnoActualizado){
        logger.info("Modificando turno");
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()){
            Turno turnoExistente = turnoOptional.get();

            if (turnoActualizado.getFecha() != null){
                turnoExistente.setFecha(turnoActualizado.getFecha());
            }
            if(turnoActualizado.getOdontologo() != null){
                turnoExistente.setOdontologo(turnoActualizado.getOdontologo());
            }
            if(turnoActualizado.getPaciente() != null){
                turnoExistente.setPaciente(turnoActualizado.getPaciente());
            }
            return turnoRepository.save(turnoExistente);
        }
        return turnoActualizado;
    }
}

