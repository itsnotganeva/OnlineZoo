package org.example.zoo.controllers;

import org.example.zoo.dao.AnimalDao;
import org.example.zoo.dao.CategoryDao;
import org.example.zoo.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/animals")
public class AnimalsController {

    private final AnimalDao animalDao;
    private final CategoryDao categoryDao;

    @Autowired
    public AnimalsController(AnimalDao animalDao, CategoryDao categoryDao) {
        this.animalDao = animalDao;
        this.categoryDao = categoryDao;
    }



    @GetMapping()
    public String index(Model model){

        model.addAttribute("animals", animalDao.index());
        return "animals/index";
    }

    @GetMapping("/{animalId}")
    public String show(@PathVariable("animalId") int animalId, Model model){
        model.addAttribute("animal", animalDao.show(animalId));
        Animal animal = animalDao.show(animalId);
        model.addAttribute("category", categoryDao.show(animal.getCategoryId()));
        return "animals/show";
    }

    @GetMapping("/new")
    public String newAnimal(@ModelAttribute("animal") Animal animal) {
        return "animals/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("animal") Animal animal) {
        animalDao.save(animal);
        return "redirect:/animals";
    }

    @GetMapping("/{animalId}/edit")
    public String edit(Model model, @PathVariable("animalId") int animalId) {
        model.addAttribute("animal", animalDao.show(animalId));
        return "animals/edit";
    }

    @PatchMapping("/{animalId}")
    public String update(@ModelAttribute("animal") Animal animal,
                         @PathVariable("animalId") int animalId) {
        animalDao.update(animalId, animal);
        return "redirect:/animals";
    }

    @DeleteMapping("/{animalId}")
    public String delete(@PathVariable("animalId") int animalId) {
        animalDao.delete(animalId);
        return "redirect:/animals";
    }
}
