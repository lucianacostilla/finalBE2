package com.example.clinica.controller;

import com.example.clinica.entities.Odontologo;
import com.example.clinica.exception.BadRequestException;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")

public class OdontologoController {
private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Optional<Odontologo> odontologo1 = odontologoService.obtenerOdontologoPorId(odontologo.getId());
        if(odontologo1 == null){
            odontologoService.guardarOdontologo(odontologo);
            return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
        }else{
            throw new BadRequestException("No se pudo crear el  odontologo.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.obtenerOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) {
        Optional<Odontologo> odontologoABuscar = odontologoService.obtenerOdontologoPorId(id);
        if(odontologoABuscar.isPresent()){
            return ResponseEntity.ok(odontologoABuscar.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping
    public ResponseEntity<String> eliminarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoABuscar = odontologoService.obtenerOdontologoPorId(id);
        if(odontologoABuscar.isPresent()){
          odontologoService.eliminarOdontologoPorId(id);
            return ResponseEntity.ok("El odontólogo ha sido eliminado.");
        }else{
            throw new ResourceNotFoundException("No se ha podido eliminar el odontologo indicado. Intentar nuevamente.");
           // return ResponseEntity.badRequest().body("No se ha podido encontrar el odontólogo indicado.");
        }
    }


    @PutMapping
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo){
            Optional<Odontologo>  odontologoABuscar = odontologoService.obtenerOdontologoPorId(odontologo.getId());
            if(odontologoABuscar.isPresent()){
                odontologoService.modificarOdontologo(odontologo.getId(), odontologo);
                return ResponseEntity.ok("Se ha actualizado con éxito el odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + ".");
            }else{
               return ResponseEntity.badRequest().body("No se puede actualizar el odontólogo indicado.");
            }
    }

}
