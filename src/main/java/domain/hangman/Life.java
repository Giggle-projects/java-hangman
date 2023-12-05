package domain.hangman;

public class Life {

    private final int life;
    private int remainingLife;

    public Life(int life) {
        this.validate(life);
        this.life = life;
        this.remainingLife = life;
    }

    private void validate(int life) {
        if (life < 1) {
            throw new IllegalArgumentException("목숨은 1 이상이어야 합니다.");
        }
    }

    @Override
    public String toString() {
        return "Life{" +
                "life=" + life +
                ", remainingLife=" + remainingLife +
                '}';
    }
}
