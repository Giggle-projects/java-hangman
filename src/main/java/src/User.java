package src;

import java.time.LocalDate;

public class User {
    private int id;
    private LocalDate regDate;
    private static int AUTO_GENERATOR = 0;

    public User() {
        AUTO_GENERATOR++;
        this.id = AUTO_GENERATOR;
        this.regDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", regDate=" + regDate +
                '}';
    }
}
