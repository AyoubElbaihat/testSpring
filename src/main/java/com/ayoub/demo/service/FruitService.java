package com.ayoub.demo.service;

import com.ayoub.demo.entity.Fruit;
import com.ayoub.demo.repository.FruitJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FruitService {
    @Autowired
    private FruitJdbcRepository fruitJdbcRepository;
    public List<Fruit> fetchAllFruits(){
        return fruitJdbcRepository.findAll();
    }
    public Optional<Fruit> fetchById(Integer id){
        return fruitJdbcRepository.findById(id);
    }
    public Optional<Fruit> addFruit(Fruit fruit){
        return Optional.ofNullable(fruitJdbcRepository.addFruit(fruit));
    }
}
