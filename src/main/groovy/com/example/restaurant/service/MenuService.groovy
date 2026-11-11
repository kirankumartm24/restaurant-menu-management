
package com.example.restaurant.service
import com.example.restaurant.entity.Menu
import com.example.restaurant.repository.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class MenuService 
{
    @Autowired
    MenuRepository menuRepository
    def getMenuById(Long id) 
    {
        def menu=menuRepository.findById(id)
        if(!menu.isPresent())
        {
            return "Menu Item not found with ID:${id}"
        }
        return menu.get();
    }
    def addMenu(Menu menu) 
    {
        if(menu.price==null||menu.price<=0) {
        return "Price must be greater than zero"
    }
        return menuRepository.save(menu)
    }
    
    List<Menu> getAllMenus()
     {
        //return menuRepository.findAll()
        menuRepository.findAll()
    }

    def updateMenu(Long id,Menu menu) 
    {
        def existingMenu=menuRepository.findById(id)
        if(!existingMenu.isPresent()) 
        {
            return "Menu Item not found with ID:${id}"
        }
       if (menu.price==null||menu.price<=0) 
       {
        return "Price must be greater than zero"
        }
        def currentMenu=existingMenu.get()
        currentMenu.name=menu.name
        currentMenu.category=menu.category
        currentMenu.price=menu.price
        currentMenu.available=menu.available
        currentMenu.isVegetarian=menu.isVegetarian
       
        return menuRepository.save(currentMenu)
    }

   def patchMenu(Long id,Menu menu) 
   {
        def existingMenu=menuRepository.findById(id)

        if(!existingMenu.isPresent()) 
        {
            return "Menu Item not found with ID:${id}"
        }
        if(menu.price!=null&&menu.price<=0)
        {
            return "Price must be greater than zero"
        }
        def currentMenu=existingMenu.get()
        if(menu.name!=null)
            currentMenu.name=menu.name

        if(menu.category!=null)
            currentMenu.category=menu.category

        if (menu.price!=null)
            currentMenu.price=menu.price

        if (menu.available!=null)
            currentMenu.available=menu.available

        if (menu.isVegetarian!=null)
            currentMenu.isVegetarian=menu.isVegetarian

        return menuRepository.save(currentMenu)
    }

    def deleteMenu(Long id)
     {
        if(!menuRepository.existsById(id))
         {
            return "Menu item not found with ID:${id}"
        }

        menuRepository.deleteById(id)
        return "Menu item deleted successfully with ID:${id}"
    }
    def getMenusByVegetarian(Boolean isVegetarian) 
    {
    return menuRepository.findByIsVegetarian(isVegetarian)
}
def getMenusByAvailability(Boolean available) {
    return menuRepository.findByAvailable(available)
}
def filterMenus(Boolean available,Boolean vegetarian) {
    return menuRepository.findByAvailableAndIsVegetarian(available, vegetarian)
}
}
