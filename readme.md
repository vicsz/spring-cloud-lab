# Spring Cloud Lab

A simple Spring Cloud "Slot Machine" :cherries: :cherries: :cherries: application, demoing cloud and microservice patterns.  

The application leverages a Random Numbers Service, a Service Registry (Eureka), and an implementation of the Circuit Breaker pattern using Mystrix. 

## 0 - Initial setup
### Create the root project directory 
```sh
$ mkdir spring-cloud-lab
```
Subsequent projects / services will be places within this folder as they are generated using the Spring Initlizer quickstart generator.

A number of seperate Java projects will be created. 

To faciliate testing it is a good idea to keep a seperate console and IDE window open for each.