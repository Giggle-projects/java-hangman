package src.hangman;

import java.util.ArrayList;
import java.util.List;

import src.util.InputHelper;

public class HangmanGame {
	private static int serialNum = 0;
	private int gameId;
	private Hangman hangman;
	private Boolean success;

	public HangmanGame(Hangman hangman) {
		serialNum++;
		this.gameId = serialNum;
		this.hangman = hangman;
		this.success = false;
	}

	public Boolean start() {
		System.out.println(hangman.getAnswer());
		InputHelper.printInfo(gameId + "번째 게임이 시작됩니다. 정답 단어는 " + hangman.getWordSize() + "글자 입니다.");
		while (hangman.getLife() > 0) {
			String alphabet = InputHelper.singleInput(hangman.toString());
			List<Integer> replaceIndexes = findIndexes(hangman.getAnswer(), alphabet);

			// 추측 실패
			if (replaceIndexes.isEmpty()) {
				hangman.decrementLife();
				if (hangman.getLife() == 0) {
					break;    // 남은 목숨 없음. 게임 종료.
				}

				hangman.incrementRound();
				continue;
			}

			// 추측 성공
			replaceIndexes.forEach(index -> hangman.replaceHiddenWord(index, alphabet));

			if (hangman.answerCheck()) {
				success = true;
				break;    // 정답 맞춤. 게임 종료.
			}

			hangman.incrementRound();
		}
		return success;
	}

	public static List<Integer> findIndexes(String word, String alphabet) {
		List<Integer> indexList = new ArrayList<>();
		int index = word.indexOf(alphabet);

		while (index != -1) {
			indexList.add(index);
			index = word.indexOf(alphabet, index + 1);
		}

		return indexList;
	}
}
