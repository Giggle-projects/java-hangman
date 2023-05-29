package src.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import src.util.InputHelper;

public class HangmanGame {
	private static int serialNum = 0;
	private int gameId;
	private int roundID;
	private SortedMap<Integer,HangmanRound> roundHistory;
	private Hangman hangman;
	private Set<String> inputHistory;
	private Boolean success;

	public HangmanGame(Hangman hangman) {
		serialNum++;
		this.gameId = serialNum;
		this.roundID = 1;
		this.roundHistory =new TreeMap<>();
		this.hangman = hangman;
		this.success = false;
	}

	public Boolean start() {
		InputHelper.printInfo(gameId + "번째 게임이 시작됩니다. 정답 단어는 " + hangman.getWordSize() + "글자 입니다.");
		while (hangman.getLife() > 0) {
			String alphabet = InputHelper.singleAlphabetInput(roundID+" 라운드 : "+hangman.toString());
			roundHistory.put(roundID,new HangmanRound(roundID, hangman.getLife(), hangman.getHiddenWord().toString(),alphabet));

			List<Integer> replaceIndexes = findIndexes(hangman.getAnswer(), alphabet);

			// 추측 실패
			if (replaceIndexes.isEmpty()) {
				hangman.decrementLife();
				if (hangman.getLife() == 0) {
					break;    // 남은 목숨 없음. 게임 종료.
				}

				incrementRound();
				continue;
			}

			// 추측 성공
			replaceIndexes.forEach(index -> hangman.replaceHiddenWord(index, alphabet));

			if (hangman.answerCheck()) {
				success = true;
				break;    // 정답 맞춤. 게임 종료.
			}

			incrementRound();
		}
		return success;
	}

	private void incrementRound() {
		this.roundID++;
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

	@Override
	public String toString() {
		StringBuilder sb =new StringBuilder();

		sb.append("\n=== Game Result ===\n");
		sb.append("게임 id : ").append(gameId);
		sb.append(", 추측 : ").append(success?"성공":"실패");
		sb.append(", 남은 목숨 : ").append(hangman.getLife());
		sb.append(", 정답 : ").append(hangman.getAnswer());
		sb.append("\n\n");
		roundHistory.forEach((key, value) ->sb.append(value.toString()));
		sb.append("===================\n");

		return sb.toString();
	}
}
