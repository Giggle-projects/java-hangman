package src.domain;

public class HangmanInfo {

    private static final String BLANK = "";
    private static final String SEPARATOR = ",";

    private static final String REGEX_REMOVE_WITHOUT_NUMBER_AND_SEPARATOR = "[^0-9" + SEPARATOR + "]";
    private static final String ERR_INPUT_CORRECT_VALUE_NUMBER_OF_GAME_AND_LIFE = "게임 횟수와 목숨을'" + SEPARATOR + "로 구분하여 차례대로 입력 해주세요.\n";

    private static final int INDEX_NUMBER_GAMES = 0;
    private static final int INDEX_LIFE = 1;

    private static final int MIN_NUMBER_GAMES = 1;
    private static final int MAX_NUMBER_GAMES = 100;

    private static final int MAX_LIFE = 1000;
    private static final int MIN_LIFE = 1;

    private final int numberGames;
    private final int life;

    private HangmanInfo(int numberGames, int life) throws IllegalArgumentException {
        validateLife(life);
        validateNumberGames(numberGames);
        this.numberGames = numberGames;
        this.life = life;
    }

    public static HangmanInfo from(String input) throws IllegalArgumentException {
        String filteredInput = input.replaceAll(REGEX_REMOVE_WITHOUT_NUMBER_AND_SEPARATOR, BLANK);
        String[] gameNumberAndLife = splitGameNumberAndLifeFrom(filteredInput);

        int numberGames = Integer.parseInt(gameNumberAndLife[INDEX_NUMBER_GAMES]);
        int life = Integer.parseInt(gameNumberAndLife[INDEX_LIFE]);

        return new HangmanInfo(numberGames, life);
    }

    private static String[] splitGameNumberAndLifeFrom(String string) throws IllegalArgumentException {
        String[] gameNumberAndLife = string.split(SEPARATOR);

        if (gameNumberAndLife.length != 2)
            throw new IllegalArgumentException(ERR_INPUT_CORRECT_VALUE_NUMBER_OF_GAME_AND_LIFE);

        return gameNumberAndLife;
    }

    public int numberGames() {
        return numberGames;
    }

    public int life() {
        return life;
    }

    private void validateNumberGames(int numberGames) throws IllegalArgumentException {
        if (MIN_NUMBER_GAMES > numberGames || MAX_NUMBER_GAMES < numberGames) {
            throw new IllegalArgumentException("게임 횟수 범위 밖의 숫자입니다.");
        }
    }

    private void validateLife(int life) throws IllegalArgumentException {
        if (MIN_LIFE > life || MAX_LIFE < life) {
            throw new IllegalArgumentException("목숨 횟수 범위 밖의 숫자입니다.");
        }
    }
}
