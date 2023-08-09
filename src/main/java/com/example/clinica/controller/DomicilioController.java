package com.example.clinica.controller;

import com.example.clinica.entities.Domicilio;
import com.example.clinica.entities.Paciente;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/domicilio")

public class DomicilioController {
    private  DomicilioService domicilioService;
    @Autowired
    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> buscarDomicilioPorId(@PathVariable Long id){
        Optional<Domicilio> domicilioABuscar = domicilioService.buscarDomicilio(id);
        if(domicilioABuscar.isPresent()){
            return ResponseEntity.ok(domicilioABuscar.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/obtener_todos")
    public ResponseEntity<List<Domicilio>> listarDomicilios(){
        return ResponseEntity.ok(domicilioService.listarTodosLosDomicilios());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDomicilioPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Domicilio> domicilioABuscar = domicilioService.buscarDomicilio(id);
        if(domicilioABuscar.isPresent()){
            domicilioService.eliminarDomicilio(domicilioABuscar.get());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se ha eliminado el domicilio con éxito.");
        }else{
            throw new ResourceNotFoundException("No se ha podido eliminar el domicilio indicado. Intentar nuevamente.");
          //  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido eliminar el domicilio indicado. Intentar nuevamente.");
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Domicilio> registrarDomicilio(@RequestBody Domicilio domicilio){
        return ResponseEntity.ok(domicilioService.agregarDomicilio(domicilio));
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarDomicilio(@RequestBody Domicilio domicilio){
        Optional<Domicilio> domicilioABuscar = domicilioService.buscarDomicilio(domicilio.getId());
        if(domicilioABuscar.isPresent()){
            domicilioService.modificarDomicilio(domicilio);
            return ResponseEntity.ok("Se ha modificado el domicilio con éxito.");
        } else {
            return ResponseEntity.badRequest().body("Se ha encontrado un error con el domicilio. Intente de nuevo.");
        }
    }
}
