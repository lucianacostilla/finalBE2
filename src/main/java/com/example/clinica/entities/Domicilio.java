package com.example.clinica.entities;


import javax.persistence.*;


@Entity
@Table
public class Domicilio {

    @Id
    @SequenceGenerator(name = "domicilio_sequence", sequenceName = "domicilio_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_sequence")
    private Long id;

    @Column
    private String calle;

    @Column
    private int numeroDeCalle;

    @Column
    private String localidad;

    @Column
    private String provincia;

    public Domicilio() {
    }

    public Domicilio(String calle, int numeroDeCalle, String localidad, String provincia) {
        this.calle = calle;
        this.numeroDeCalle = numeroDeCalle;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeroDeCalle() {
        return numeroDeCalle;
    }

    public void setNumeroDeCalle(int numeroDeCalle) {
        this.numeroDeCalle = numeroDeCalle;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}