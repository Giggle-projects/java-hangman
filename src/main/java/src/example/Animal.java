package src.example;

import java.util.ArrayList;
import java.util.List;

public enum Animal implements Example {

    CAT("cat"), FROG("frog"), CHICKEN("chicken"), TURTLE("turtle"), CRAB("crab"),
    RABBIT("rabbit"), SHARK("shark"), CROCODILE("crocodile"), GIRAFFE("giraffe"), COW("cow"),
    HORSE("horse"), BUTTERFLY("butterfly"), BULL("bull"), PIG("pig"), RHINO("rhino"),
    SHEEP("sheep"), SNAKE("snake"), PANDA("panda"), EAGLE("eagle"), SWAN("swan");

    private final String name;

    Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public List<String> getNameList(){
        List<String> animalList = new ArrayList<>();

        for (Animal animal : Animal.values()) {
            animalList.add(animal.getName());
        }

        return animalList;
    }
}
