package com.example.clinica.controller;
import com.example.clinica.entities.Turno;
import com.example.clinica.exception.ResourceNotFoundException;
import com.example.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
@Autowired
    private TurnoService turnoService;

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id){
        Optional<Turno> turnoABuscar = turnoService.obtenerTurnoPorId(id);
        if(turnoABuscar.isPresent()){
            return ResponseEntity.ok(turnoABuscar.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.obtenerTurnos());

    }

    @DeleteMapping
    public ResponseEntity<String> eliminarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoABuscar = turnoService.obtenerTurnoPorId(id);
        if(turnoABuscar.isPresent()) {
            turnoService.eliminarTurnoPorId(id);
            return ResponseEntity.ok("Se ha eliminado correctamente el turno.");
        }else{
            throw new ResourceNotFoundException("No se ha podido eliminar el turno indicado. Intentar nuevamente.");
         //return ResponseEntity.badRequest().body("No se ha podido eliminar el turno.");
        }
    }
    @PutMapping
    public ResponseEntity<String> modificar(@PathVariable Long id, @RequestBody Turno turnoModificado){
        Optional<Turno> turnoOptional = turnoService.obtenerTurnoPorId(id);
        if (turnoOptional.isPresent()){
            turnoService.actualizarTurno(id, turnoModificado);
            return ResponseEntity.ok().body("Se ha modificado con éxito!");
        }else{
            return ResponseEntity.ok().body("No existe un turno con id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody Turno turno) throws ResourceNotFoundException {
        turnoService.guardarTurno(turno);
        return ResponseEntity.ok().body("Se ha agregado el turno con éxito!");
    }
}

