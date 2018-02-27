package com.example.slotmachineservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SlotMachineController {

    private RestTemplate restTemplate;

    @Value("${slot.machine.symbols}")
    private String[] slotMachineSymbols;

    @Autowired
    public SlotMachineController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @RequestMapping
    @HystrixCommand(fallbackMethod = "defaultSpinResult")
    public String spin(){
        return String.format("%s %s %s", getSingleSpinResult(), getSingleSpinResult(), getSingleSpinResult());
    }

    private String getSingleSpinResult(){
        int randomNumber = restTemplate.getForObject("http://random-number-service/randomNumber", Integer.class);
        return slotMachineSymbols[Math.abs(randomNumber%slotMachineSymbols.length)];
    }

    private String defaultSpinResult() {
        return "? ? ?";
    }

}