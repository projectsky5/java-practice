package org.example;

public class Parrot extends Animal implements Flyable, Herbivore{

    public Parrot(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Кар!");
    }

    @Override
    public String getType() {
        return "Parrot";
    }

    @Override
    public void fly() {
        System.out.println("Полетел!");
    }

    @Override
    public boolean isHerbivore() {
        return true;
    }
}
