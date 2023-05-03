package com.ayoub.demo.api;

import com.ayoub.demo.entity.Fruit;
import com.ayoub.demo.repository.FruitJdbcRepository;
import com.ayoub.demo.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fruits")
public class FruitController {
    @Autowired
    private FruitService fruitService;
    @GetMapping
    public List<Fruit> all(){
        return fruitService.fetchAllFruits();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Fruit> fetchFruitById(@PathVariable("id") int id){
        Optional<Fruit> fruitOptional= fruitService.fetchById(id);
        if(fruitOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fruitOptional.get());
    }
    @PostMapping
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit fruit){
        Optional<Fruit> fruitOptional = fruitService.addFruit(fruit);
        if(fruitOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fruitOptional.get());
    }
}
