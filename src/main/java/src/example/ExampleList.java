package src.example;

public enum ExampleList {
    ANIMAL("Animal"), BODY("Body");

    private String name;

    ExampleList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
