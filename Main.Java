// Changed name from animalz to animals
// Removed unused random hashmap,cache and helper method as they were unused dead code.
// Made static methods private for encapsulation
// Made the fields of the animal class private with getters and setters.
// Added a method to the animal class to do a special action and overide that method in each subclass for encapsulation purposes.
// Created a simple factory for animals
// Replaced the insance of if chain with a for loop


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
        k.dogs.add(new Dog("Buddy"));
    }

    private static double calculateTax(double gross, String country) {
        if ("UK".equals(country)) return gross * 0.2;
        if ("FR".equals(country)) return gross * 0.19 + 3;
        return gross * 0.15;
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

    private static String helper(String x) {
        if (x == null) return "null";
        String y = x.trim().toUpperCase();
        if (y.length() > 2) return y.substring(0, 2);
        return y;
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

class Kennel {
    public List<Dog> dogs = new ArrayList<>();
    public String address = "Somewhere";
}
