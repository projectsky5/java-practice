package org.example;

public class Lion extends Animal implements Herbivore{

    public Lion(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Ррр!");
    }

    @Override
    public String getType() {
        return "Lion";
    }

    @Override
    public boolean isHerbivore() {
        return false;
    }
}
