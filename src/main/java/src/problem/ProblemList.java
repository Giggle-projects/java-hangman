package src.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum Animal implements Problem {

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

enum Body implements Problem {

    EYES("eyes"), TEETH("teeth"), TOES("toes"), HEAD("head"), EYEBROW("eyebrow"),
    EARS("ears"), HAIR("hair"), SHOULDER("shoulder"), TONGUE("tongue"), BONES("bones"),
    HAND("hand"), FINGER("finger"), KNEE("knee"), MOUSTACHE("moustache"), ANKLE("ankle"),
    NOSE("node"), LEG("leg"), THUMB("thumb"), NECK("neck"), HEEL("heel");
    private final String name;

    Body(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public List<String> getNameList() {
        List<String> bodyList = new ArrayList<>();

        for (Body body : Body.values()) {
            bodyList.add(body.getName());
        }

        return bodyList;
    }
}

public enum ProblemList {
    ANIMAL("Animal",Animal.class.getName()),
    BODY("Body",Body.class.getName());

    private final String name;
    private final String className;

    ProblemList(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public static List<String> getContentsByCategoryName(String categoryName){
        List<String> problemList;
        String problemClassName = null;

        for (ProblemList problem : ProblemList.values()) {
            if (problem.name.equals(categoryName)) {
                problemClassName = problem.getClassName();
            }
        }

        if (problemClassName == null){
            return null;
        }

        try {
            Class<?> enumClass = Class.forName(problemClassName);
            Problem ex = (Problem) enumClass.getEnumConstants()[0];
            problemList = ex.getNameList();

            Collections.shuffle(problemList);

            return problemList;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
