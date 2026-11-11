package com.example.restaurant.entity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType

@Entity
@Table(name="restaurant_menu")
class Menu {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id
    String name
    String category
    Integer price
    Boolean available
    Boolean isVegetarian
}