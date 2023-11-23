package com.example.RestaurantService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuisine_type")
public class CuisineType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "cuisine_generator")
    @SequenceGenerator(
            name = "cuisine_generator",
            sequenceName = "cuisine_sequence_name",
            allocationSize = 1
    )
    @Column(name = "cuisine_id")
    private Integer cuisineId;

    @Column(name = "cuisine_type", nullable = false)
    @Pattern(regexp = "^[A-Za-z\\s-]+$", message = "Invalid cuisine type")
    private String cuisineType;

    @JsonBackReference
    @ManyToOne
    private Restaurant restaurant;
}
