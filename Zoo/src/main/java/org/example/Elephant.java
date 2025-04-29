package org.example;

public class Elephant extends Animal implements Herbivore{
    public Elephant(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Уууу!");
    }

    @Override
    public String getType() {
        return "Elephant";
    }


    @Override
    public boolean isHerbivore() {
        return true;
    }
}
