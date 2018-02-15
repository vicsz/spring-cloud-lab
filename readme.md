# Spring Cloud Lab

A simple Spring Cloud "Slot Machine" :cherries: :cherries: :cherries: application, demoing cloud and microservice patterns.  

The lab consists of a number of interconntected components including:
- A Slot Machine Service
- A Random Number Service
- A Service Registry (using Eureka)

## 0 - Initial setup
### Create the root project directory 
```sh
$ mkdir spring-cloud-lab
```
Subsequent projects / services will be places within this folder as they are generated using the Spring Initlizer quickstart generator.

A number of seperate Java projects will be created. 

To faciliate testing it is a good idea to keep a seperate console and IDE window open for each.

## 1 - Create the Random Number Service  
### 1.1 - Generate the Spring Boot Template from https://start.spring.io
Stick to the default settings, however update:
- artifact name to random-number-service
- for dependencies add Web, Actuator  

### 1.2 - Download the project folder into our spring-cloud-lab directory
### 1.3 - Open the project by importing the generated pom.xml with your IDE of choice
### 1.4 - Update the Code Base 

Implement a /randomNumber endpoint that returns a random integer. 

This can be done by creating a RandomNumberController Java class file with:


```java
@RestController
public class RandomNumberController {

    @RequestMapping
    public int getRandomNumber(){
        return new Random().nextInt();
    }
}
```
### 1.5 - Run the application (from /spring-cloud-lab/random-number-service)
```sh
$ ./mvnw spring-boot:run
```

### 1.6 - Test the /randomNumber endpoint at localhost:8080/randomNumber

You should see a randomly generated number response. 

## 2 - Create the Slot Machine Service
### 2.1 - Generate the Spring Boot Template from https://start.spring.io
Stick to the default settings, however update:
- artifact name to slot-machine-service
- for dependencies add Web, Actuator  

### 2.2 - Download the project folder into our spring-cloud-lab directory
### 2.3 - Open the project by importing the generated pom.xml with your IDE of choice
### 2.4 - Update the Code Base 

Update the application.properties file to not have a conflicting port with out Random Number generator service. 

```properties
server.port=8081
```

Implement a /spin endpoint that returns 3 random slot machine symbols. i.e. Cherry, Bar, Orange, Plum, etc

This can be done by creating a SlotMachineController class file with:


```java
@RestController
public class SlotMachineController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping
    public String spin(){

        String[] slotMachineSymbols = {"Cherry", "Bar", "Orange", "Plum"};
        
        return "Cherry cherry cherry"; //TODO update this to return 3 random slot machine symbols using the below example usage of the Random Number Service .. numerous ways exist of doing this ! 
     
         // EXAMPLE int randomNumber = restTemplate.getForObject("http://localhost:8080/randomNumber", Integer.class);
    }
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

__Implenent your own solution at the TODO mark or scroll down for one such solution__

```java
return IntStream.range(0, 3).mapToObj(x-> {
            int randomNumber = restTemplate.getForObject("http://localhost:8080/randomNumber", Integer.class);
            return slotMachineSymbols[Math.abs(randomNumber%slotMachineSymbols.length)]}
        ).collect(Collectors.joining(" "));
```


### 2.5 - Run the application (from /spring-cloud-lab/slot-machine-service)
```sh
$ ./mvnw spring-boot:run
```

### 2.6 - Test the spin endpoint at localhost:8081/spin

You should get a randomly generated slot machine response.
