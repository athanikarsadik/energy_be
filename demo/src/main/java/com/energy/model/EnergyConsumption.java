package com.energy.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "energy_consumption")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnergyConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private double energyConsumption;
    private double energyGeneration;
    private double min;
    private double max;
    private LocalDateTime timestamp;

}
