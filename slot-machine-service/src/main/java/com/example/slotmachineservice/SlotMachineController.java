package com.example.slotmachineservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class SlotMachineController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping
    public String spin(){

        String[] slotMachineSymbols = {"Cherry", "Bar", "Orange", "Plum"};

        return IntStream.range(0, 3).mapToObj(x-> {
            int randomNumber = restTemplate.getForObject("http://localhost:8080/randomNumber", Integer.class);
            return slotMachineSymbols[Math.abs(randomNumber%slotMachineSymbols.length)];}
        ).collect(Collectors.joining(" "));

    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}