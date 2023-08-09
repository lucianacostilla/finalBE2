package com.example.clinica.service;

import com.example.clinica.controller.TurnoController;
import com.example.clinica.entities.Domicilio;
import com.example.clinica.repository.DomicilioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {
    Logger logger = Logger.getLogger(TurnoController.class);
    @Autowired
    private DomicilioRepository domicilioRepository;

    public List<Domicilio> listarTodosLosDomicilios() {
        logger.info("Listando domicilios.");
        List<Domicilio> domicilios = domicilioRepository.findAll();
        return domicilios;
    }

    public Domicilio agregarDomicilio(Domicilio domicilio) {
        logger.info("agregando domicilio.");
        domicilioRepository.save(domicilio);
        return domicilio;
    }

    public void modificarDomicilio(Domicilio domicilio) {
        logger.info("modificando domicilio.");
        domicilioRepository.save(domicilio);
    }

    public void eliminarDomicilio(Domicilio domicilio) {
        logger.info("eliminando domicilio.");
       domicilioRepository.delete(domicilio);
    }

    public Optional<Domicilio> buscarDomicilio(Long id) {
        logger.info("buscando domicilio.");
        return domicilioRepository.findById(id);
    }

}
