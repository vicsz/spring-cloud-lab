package com.example.slotmachineservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class SlotMachineController {


    private RestTemplate restTemplate;

    @Autowired
    public SlotMachineController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @RequestMapping
    @HystrixCommand(fallbackMethod = "defaultResult")
    public String spin(){

        String[] slotMachineSymbols = {"Cherry", "Bar", "Orange", "Plum"};

        return IntStream.range(0, 3).mapToObj(x-> {
            int randomNumber = restTemplate.getForObject("http://random-number-service/randomNumber", Integer.class);
            return slotMachineSymbols[Math.abs(randomNumber%slotMachineSymbols.length)];}
        ).collect(Collectors.joining(" "));

    }

    private String defaultResult() {
        return "? ? ?";
    }

}