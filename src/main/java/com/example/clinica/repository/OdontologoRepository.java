package com.example.clinica.repository;
import com.example.clinica.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository <Odontologo, Long>{

}
