package src.problem;

import src.util.Message;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum Animal implements Problem {

    CAT("cat"), FROG("frog"), CHICKEN("chicken"), TURTLE("turtle"), CRAB("crab"),
    RABBIT("rabbit"), SHARK("shark"), CROCODILE("crocodile"), GIRAFFE("giraffe"), COW("cow"),
    HORSE("horse"), BUTTERFLY("butterfly"), BULL("bull"), PIG("pig"), RHINO("rhino"),
    SHEEP("sheep"), SNAKE("snake"), PANDA("panda"), EAGLE("eagle"), SWAN("swan");

    private final String name;

    Animal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
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

    @Override
    public String getName() {
        return name;
    }
}

public enum ProblemList {
    ANIMAL("Animal", Animal.values()),
    BODY("Body", Body.values());

    private final String problemType;
    private final List<String> problems;

    ProblemList(String problemType, Problem[] problemContents) {
        this.problemType = problemType;
        this.problems = Arrays.stream(problemContents).map(Problem::getName).collect(Collectors.toList());
    }

    public String getProblemType() {
        return problemType;
    }

    public static List<String> getContentsByCategoryName(String problemType){
        for (ProblemList problem : ProblemList.values()) {
            if (problem.getProblemType().equals(problemType)) {
                return problem.problems;
            }
        }
        throw new IllegalArgumentException(Message.ERR_MSG_INVALID_INPUT_CATEGORY_RANGE);
    }
}
