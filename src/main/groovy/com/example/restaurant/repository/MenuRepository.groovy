package com.example.restaurant.repository

import com.example.restaurant.entity.Menu

import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository extends JpaRepository<Menu,Long>
{
   List<Menu> findByAvailable(Boolean available)
List<Menu> findByIsVegetarian(Boolean isVegetarian)
List<Menu> findByAvailableAndIsVegetarian(Boolean available,Boolean isVegetarian)
}