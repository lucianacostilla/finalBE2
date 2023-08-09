package com.example.clinica.service;
import com.example.clinica.entities.Paciente;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/*listar, agregar, modificar y eliminar pacientes.*/
@Service
public class PacienteService {
    Logger logger = Logger.getLogger(PacienteService.class);
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodosLosPacientes() {
        logger.info("listando pacientes.");
        List<Paciente>pacientes = pacienteRepository.findAll();
        return pacientes;
    }

    public Paciente agregarPaciente(Paciente paciente) {
        logger.info("agregando pacientes.");
        pacienteRepository.save(paciente);
        return paciente;
    }
    public Paciente modificarPaciente(Long id, Paciente pacienteNuevo){
        logger.info("modificando pacientes.");
       // logger.debug("Modificando el paciente con id: " + id);
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()){
            Paciente pacienteExistente = pacienteOptional.get();
            if (pacienteNuevo.getNombre() != null){
                pacienteExistente.setNombre(pacienteNuevo.getNombre());
            }
            if(pacienteNuevo.getApellido() != null){
                pacienteExistente.setApellido(pacienteNuevo.getApellido());
            }
            if(pacienteNuevo.getDni() != null){
                pacienteExistente.setDni(pacienteNuevo.getDni());
            }
            if(pacienteNuevo.getFechaDeAlta() != null){
                pacienteExistente.setFechaDeAlta(pacienteNuevo.getFechaDeAlta());
            }
            if(pacienteNuevo.getDomicilio() != null){
                pacienteExistente.setDomicilio(pacienteNuevo.getDomicilio());
            }
            return pacienteRepository.save(pacienteExistente);
        }
        return pacienteNuevo;
    }

    public void eliminarPaciente(Paciente paciente) {
        logger.info("eliminando pacientes.");
        pacienteRepository.delete(paciente);
    }


    public Optional<Paciente> buscarPaciente(Long id){
        logger.info("buscando pacientes.");
        return pacienteRepository.findById(id);
    }




    public void eliminarPacientePorId(Long id){
        logger.info("eliminando pacientes.");
        pacienteRepository.deleteById(id);
    }
}
