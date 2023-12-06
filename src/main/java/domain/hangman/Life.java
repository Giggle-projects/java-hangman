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

    public boolean isDone() {
        return remainingLife == 0;
    }

    public void recover() {
        remainingLife = life;
    }

    public void decrease() {
        if (isDone()) {
            throw new IllegalArgumentException("남아있는 목숨이 없어 게임을 진행할 수 없습니다.");
        }
        remainingLife -= 1;
    }

    public int getRemainingLife() {
        return remainingLife;
    }

    @Override
    public String toString() {
        return "Life{" +
                "life=" + life +
                ", remainingLife=" + remainingLife +
                '}';
    }
}
