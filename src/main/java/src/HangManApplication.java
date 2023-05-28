package src;

import src.hangman.Hangman;
import src.hangman.HangmanGame;
import src.util.InputHelper;

public class HangManApplication {
    private static int playCount;
    private static int life;
    public static void main(String[] args) {
        Integer[] inputs=InputHelper.multiIntegerInput("게임 횟수와 목숨을 입력하세요.",", ");
        playCount=inputs[0];
        life=inputs[1];

        while (playCount-->0){
            HangmanGame newGame=new HangmanGame(new Hangman(life));

            boolean result= newGame.start();
            InputHelper.printInfo(result ? "축하합니다. 정답입니다.\n":"실패입니다. 다시 도전해보세요!\n");

            if(playCount>0){
                InputHelper.printInfo("다음 게임을 시작합니다.");
            }
        }
    }
}
