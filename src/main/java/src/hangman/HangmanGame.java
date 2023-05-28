package src.hangman;

import java.util.ArrayList;
import java.util.List;

import src.util.InputHelper;

public class HangmanGame {
	private static int serialNum=0;
	private int gameId;
	private Hangman hangman;
	private Boolean correct;
	public HangmanGame(Hangman hangman) {
		serialNum++;
		this.gameId=serialNum;
		this.hangman = hangman;
		this.correct=false;
	}

	public Boolean start(){
		InputHelper.printInfo(gameId+"번째 게임이 시작됩니다. 정답 단어는 "+hangman.getWordSize()+"글자 입니다.");
		while(hangman.getLife()>0){
			InputHelper.singleInput()
		}
		return correct;
	}

	public static List<Integer> findIndexes(String word, String alphabet) {
		List<Integer> indexList = new ArrayList<Integer>();
		int index = word.indexOf(alphabet);

		while (index != -1) {
			indexList.add(index);
			index = word.indexOf(alphabet, index + 1);
		}

		return indexList;
	}
}
