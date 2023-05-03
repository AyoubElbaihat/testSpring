package com.ayoub.demo.api;

import com.ayoub.demo.entity.Fruit;
import com.ayoub.demo.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class FruitPageController {
    @Autowired
    private FruitService fruitService;
    @GetMapping("/list-fruits")
    public String all(Model model) {
        List<Fruit> fruitList = fruitService.fetchAllFruits();
        model.addAttribute("fruitList", fruitList);
        return "fruits";
    }
    @GetMapping("/add-fruit")
    public String addFruit(@ModelAttribute Fruit fruit, Model model) {
        model.addAttribute("fruit", fruit);
        return "add-fruit";
    }
    @PostMapping("/add-fruit")
    public String add(@ModelAttribute Fruit fruit) {
        fruitService.addFruit(fruit);
        return "redirect:/list-fruits";
    }

    @GetMapping("/fruit/{id}")
    public String fetchFruitById(@PathVariable("id") int id,Model model){
        Optional<Fruit> fruit = fruitService.fetchById(id);
        model.addAttribute("fruit", fruit.get());
        return "detail-fruit";
    }

}