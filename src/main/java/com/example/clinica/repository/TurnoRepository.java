package com.example.clinica.repository;
import com.example.clinica.entities.Paciente;
import com.example.clinica.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    //Optional<Turno> findByDate(LocalDate fecha);

}
