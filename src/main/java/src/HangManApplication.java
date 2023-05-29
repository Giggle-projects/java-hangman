package src;

import static src.exception.ErrorCode.*;

import src.exception.InvalidInputFormatException;
import src.hangman.GameLauncher;
import src.hangman.Hangman;
import src.hangman.HangmanGame;
import src.util.InputHelper;

public class HangManApplication {

    private static int playCount;
    private static int life;
    public static void main(String[] args) {
        while (true){
            try{
                Integer[] inputs = InputHelper.multiIntegerInput("게임 횟수와 목숨을 입력하세요.");

                if (inputs.length != 2) {
                    throw new InvalidInputFormatException(INVALID_INPUT_COUNT.getMessage());
                }
                playCount = inputs[0];
                life = inputs[1];
                break;
            }catch(InvalidInputFormatException e){
               InputHelper.printInfo(e.getMessage());
            }
        }

        while (playCount-->0){
            GameLauncher gameLauncher =new GameLauncher(life);
            gameLauncher.start();

            if(playCount>0){
                InputHelper.printInfo("다음 게임을 시작합니다.");
            }
        }
    }
}
