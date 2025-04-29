package org.example;

import java.util.List;

public class ApplicationRunner {
    public static void main(String[] args) {
        List<Animal> animals = List.of(
                new Lion("Leva", 11),
                new Elephant("Slonik", 20),
                new Parrot("Kesha", 4)
        );

        animals.forEach(animal -> {
            System.out.printf("Тип: %s ", animal.getType());
            System.out.printf("Имя: %s ", animal.name);
            System.out.printf("Возраст: %d\n", animal.age);
            animal.makeSound();
        });

        System.out.println("Летающие:");
        for(Animal animal : animals) {
            if(animal instanceof Flyable){
                System.out.println(animal.getType());
                ((Flyable) animal).fly();
            }
        }

        System.out.println("Хищники или травоядные:");
        for(Animal animal : animals) {
            if(animal instanceof Herbivore){
                String herb = ((Herbivore) animal).isHerbivore() ? "Травоядный" : "Хищник";
                System.out.printf("%s - %s\n", animal.getType(), herb);
            }
        }
    }
}
