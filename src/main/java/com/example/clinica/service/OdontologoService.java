package com.example.clinica.service;

import com.example.clinica.entities.Odontologo;
import com.example.clinica.repository.OdontologoRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private static final Logger logger = LogManager.getLogger(TurnoService.class);

    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo){
        logger.debug("Guardando odontologo con nombre: " + odontologo.getNombre());
        return odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologoPorId(Long id){
        logger.debug("Eliminando odontologo con id: " + id);
        odontologoRepository.deleteById(id);
    }

    public List<Odontologo> obtenerOdontologos(){
        logger.debug("Obteniendo todos los odontologos");
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> obtenerOdontologoPorId(Long id){
        logger.debug("Buscando el odontologo con id: " + id);
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()){
            return odontologo;
        }
        return null;
    }

    public Odontologo modificarOdontologo(Long id,Odontologo odontologoActualizado){
        logger.debug("Modificando odontologo");
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if (odontologoOptional.isPresent()){
            Odontologo odontologoExistente = odontologoOptional.get();

            if (odontologoActualizado.getNombre() != null){
                odontologoExistente.setNombre(odontologoActualizado.getNombre());
            }
            if (odontologoActualizado.getApellido() != null){
                odontologoExistente.setApellido(odontologoActualizado.getApellido());
            }
            if (odontologoActualizado.getNumeroDeMatricula() != null){
                odontologoExistente.setNumeroDeMatricula(odontologoActualizado.getNumeroDeMatricula());
            }
            return odontologoRepository.save(odontologoExistente);
        }
        return odontologoActualizado;
    }

}