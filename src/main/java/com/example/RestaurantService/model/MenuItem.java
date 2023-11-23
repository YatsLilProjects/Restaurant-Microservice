package com.example.RestaurantService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "menu_generator")
    @SequenceGenerator(
            name = "menu_generator",
            sequenceName = "menu_sequence_name",
            allocationSize = 1
    )
    @Column(name = "menu_item_id")
    private Integer menuItemId;

    @Column(name = "menu_item_name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9\\s-]+$", message = "Invalid menu item name")
    private String menuItemName;

    @Column(name = "menu_item_price", nullable = false)
    @Positive(message = "Menu item price must be a positive number")
    private double menuItemPrice;

    @Column(name="menu_item_desc")
    private String menuItemDesc;

    @Column(name = "menu_item_image")
    private String menuItemImage;

    private boolean vegetarian;

    @JsonBackReference
    @ManyToOne
    private Restaurant restaurant;

}
