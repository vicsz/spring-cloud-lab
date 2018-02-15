package com.example.randomnumberservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomNumberController {

    @RequestMapping
    public int getRandomNumber(){
        return new Random().nextInt();
    }
}