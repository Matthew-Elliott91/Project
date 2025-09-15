// Changed name from animalz to animals
// Removed unused random hashmap,cache and helper method as they were unused dead code.
// Made static methods private for encapsulation
// Made the fields of the animal class private with getters and setters.
// Added a method to the animal class to do a special action and overide that method in each subclass for encapsulation purposes.
// Created a simple factory for animals
// Replaced the insance of if chain with a for loop
// Better encapsulated the kennel class
// Removed the hardcoded numbers from within the calculateTax method and replaced them with fields

import java.util.*;
import java.time.*;

public class Main {
   

    public static void main(String[] args) {
        java.util.List<Animal> animals = new java.util.ArrayList<Animal>();
        animals.add(AnimalFactory.create(Species.DOG, "Rex"));
        animals.add(AnimalFactory.create(Species.CAT, "Mittens"));
        animals.add(AnimalFactory.create(Species.RABBIT, "Flopsy"));

        System.out.println("=== Starter App ===");

        new Thread(() -> {
            try {
                Thread.sleep(333);
                System.out.println("[BG] did something probably important");
            } catch (InterruptedException ignored) {}
        }).start();

        for (Animal a : animals) {
            a.speak();
            a.doSpecialAction();   
            a.move();
            a.eat("food");
            System.out.println();
        }

        double bill = calculateTax(123.45, "UK");
        System.out.println("Tax rough calc: " + bill);

        saveAnimalsToFile("animals.json");

        System.out.println("Report:");
        printReport(Arrays.asList("OK", "WARN", "TODO"));

        Kennel k = new Kennel();
        k.addDog(new Dog("Buddy"));
    }

    private static final double UK_RATE = 0.20;
    private static final double FR_RATE = 0.19;
    private static final double DEFAULT_RATE = 0.15;
    private static final double FR_SURCHARGE = 3.0;


    private static final String COUNTRY_UK = "UK";
    private static final String COUNTRY_FR = "FR";

   private static double calculateTax(double gross, String country) {
    if (COUNTRY_UK.equals(country)) return gross * UK_RATE;
    if (COUNTRY_FR.equals(country)) return gross * FR_RATE + FR_SURCHARGE;
    return gross * DEFAULT_RATE;
}


    private static void printReport(List<String> lines) {
        System.out.println("---- REPORT ----");
        for (int i = 0; i < lines.size(); i = i + 1) {
            System.out.println((i+1) + ") " + lines.get(i));
        }
        System.out.println("Generated at: " + LocalDateTime.now());
    }

    private static void saveAnimalsToFile(String path) {
        System.out.println("Saving to " + path + " ...");
    }

}
enum Species {
     DOG, CAT, RABBIT, UNKNOWN
}
   
final class AnimalFactory {
    private AnimalFactory() {} 

    static Animal create(Species species, String name) {
        switch (species) {
            case DOG:    return new Dog(name);
            case CAT:    return new Cat(name);
            case RABBIT: return new Rabbit(name);
            default:     return new Animal(name, 4) { 
                @Override public void speak() { System.out.println(getName() + " says: ..."); }
            };
        }
    }
}    


abstract class Animal {
    private String name;
    private int legs;

    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }

    public String getName() { return name; }
    public int getLegs() { return legs; }

    public abstract void speak();

    public void move() {
        System.out.println(name + " moves somehow on " + legs + " legs.");
    }

    public void eat(String food) {
        System.out.println(name + " eats " + food + ".");
    }

    public void doSpecialAction(){
        System.out.println(getName() + " does animal stuff.");
    }
}

class Dog extends Animal {
    public Dog(String name) { super(name, 4); }
    @Override public void speak() { System.out.println(getName() + " says: woof"); }
    @Override public void doSpecialAction() { System.out.println(getName() + " chases tail."); }
}

class Cat extends Animal {
    public Cat(String name) { super(name, 4); }
    @Override public void speak() { System.out.println(getName() + " says: meow"); }
    @Override public void doSpecialAction() { System.out.println(getName() + " ignores you."); }
}

class Rabbit extends Animal {
    public Rabbit(String name) { super(name, 4); }
    @Override public void speak() { System.out.println(getName() + " says: squeak"); }
    @Override public void doSpecialAction() { System.out.println(getName() + " nibbles something?"); }
}

final class Kennel {
    private final java.util.List<Dog> dogs = new java.util.ArrayList<Dog>();
    private String address = "Somewhere";

    void addDog(Dog d) { dogs.add(d); }
    java.util.List<Dog> getDogs() {
        return java.util.Collections.unmodifiableList(dogs);
    }
    String getAddress() { return address; }
    void setAddress(String a) { this.address = a; }
}
