package com.example.clinica.controller;

import com.example.clinica.entities.Paciente;
import com.example.clinica.exception.BadRequestException;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId (@PathVariable Long id){
        Optional<Paciente> pacienteABuscar = pacienteService.buscarPaciente(id);
        if(pacienteABuscar.isPresent()){
            return ResponseEntity.ok(pacienteABuscar.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @GetMapping("/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email){
        Optional<Paciente> pacienteABuscar = pacienteService.buscarPacientePorEmail(email);
        if(pacienteABuscar.isPresent()){
            return ResponseEntity.ok(pacienteABuscar.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
     */


    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarTodosLosPacientes());
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarPacientePorId (@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteABuscar = pacienteService.buscarPaciente(id);
        if(pacienteABuscar.isPresent()){
            pacienteService.eliminarPacientePorId(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se ha eliminado el paciente con éxito.");
        }else{
            throw new ResourceNotFoundException ("No se ha podido eliminar el paciente indicado. Intentar nuevamente.");
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido eliminar el paciente indicado. Intentar nuevamente.");
        }
    }


    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente (@RequestBody Paciente paciente) throws BadRequestException {
        Optional<Paciente> paciente1 = pacienteService.buscarPaciente(paciente.getId());
        if(paciente1 == null){
            pacienteService.agregarPaciente(paciente);
            return ResponseEntity.ok(pacienteService.agregarPaciente(paciente));
        }else{
            throw new BadRequestException("No se pudo crear el  paciente..");
        }
    }


    @PutMapping
    public ResponseEntity<String> modificarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteABuscar = pacienteService.buscarPaciente(paciente.getId());
        if(pacienteABuscar.isPresent()){
            pacienteService.modificarPaciente(paciente.getId(), paciente);
            return ResponseEntity.ok("Se ha modificado el paciente " + paciente.getNombre() + " con éxito.");
        } else {
            return ResponseEntity.badRequest().body("Se ha encontrado un error con el paciente " + paciente.getNombre() + ". Intente de nuevo.");
        }
    }
}
