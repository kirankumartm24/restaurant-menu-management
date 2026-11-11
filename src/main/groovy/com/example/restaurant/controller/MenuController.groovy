package com.example.restaurant.controller

import com.example.restaurant.entity.Menu
import com.example.restaurant.service.MenuService

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/api/menu")
class MenuController
{

    @Autowired
    MenuService menuService

    @PostMapping
    def addMenu(@RequestBody Menu menu) 
    {

        def result=menuService.addMenu(menu)
        if(result instanceof String)
        {
            return ResponseEntity.badRequest().body(result)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }
    @GetMapping
    def getAllMenus() 
    {
        return ResponseEntity.ok(menuService.getAllMenus())
    }
    @GetMapping("/{id}")
    def getMenuById(@PathVariable("id") Long id) 
    {

        def result=menuService.getMenuById(id)
        if(result instanceof String) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result)
        }
        return ResponseEntity.ok(result)
    }
    @PutMapping("/{id}")
    def updateMenu(@PathVariable("id") Long id,@RequestBody Menu menu)
     {

        def result=menuService.updateMenu(id,menu)
        if(result instanceof String) 
        {
            if(result.contains("not found")) 
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result)
            }

            return ResponseEntity.badRequest().body(result)
        }
        return ResponseEntity.ok(result)
    }
    @PatchMapping("/{id}")
    def patchMenu(@PathVariable("id") Long id,@RequestBody Menu menu) 
    {

        def result=menuService.patchMenu(id,menu)
        if (result instanceof String) 
        {
            if(result.contains("not found")) 
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result)
            }
            return ResponseEntity.badRequest().body(result)
        }

        return ResponseEntity.ok(result)
    }
    @DeleteMapping("/{id}")
    def deleteMenu(@PathVariable("id") Long id)
     {

        def result=menuService.deleteMenu(id)

        if (result instanceof String&&result.contains("not found")) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result)
        }

        return ResponseEntity.ok(result)
    }
    @GetMapping("/filter/vegetarian")
   def getMenusByVegetarian(@RequestParam("vegetarian") Boolean vegetarian) {
    return ResponseEntity.ok(menuService.getMenusByVegetarian(vegetarian))
   }
   @GetMapping("/filter/availability")
def getMenusByAvailability(@RequestParam("available") Boolean available) {
    return ResponseEntity.ok(menuService.getMenusByAvailability(available))
}
@GetMapping("/filter")
def filterMenus(@RequestParam("available") Boolean available,@RequestParam("vegetarian") Boolean vegetarian) {
    def result=menuService.filterMenus(available, vegetarian)
    return ResponseEntity.ok(result)
}
}
