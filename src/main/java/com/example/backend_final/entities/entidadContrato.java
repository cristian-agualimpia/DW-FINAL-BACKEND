package com.example.backend_final.entities;

import java.sql.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE arrendador SET status = 1 WHERE id=?")
public class EntidadContrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, length = 255)
    private String identificador;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false, length = 255)
    private String nombreContratante;

    @Column(nullable = false, length = 255)
    private String documentoContratante;

    @Column(nullable = false, length = 255)
    private String nombreContratantista;

    @Column(nullable = false, length = 255)
    private String documentoContratantista;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
}