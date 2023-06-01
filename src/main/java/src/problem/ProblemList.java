package src.problem;

public enum ProblemList {
    ANIMAL("Animal"), BODY("Body");

    private final String name;

    ProblemList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
