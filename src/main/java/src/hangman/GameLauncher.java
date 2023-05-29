package src.hangman;

	import java.util.ArrayList;
	import java.util.HashSet;
	import java.util.List;
	import java.util.Set;

	import src.exception.DuplicateTryException;
	import src.util.InputHelper;

public class GameLauncher {
	private int serialNum = 0;
	private int roundCount;
	private Set inputHistory;
	private HangmanGame hangmanGame;

	public GameLauncher(int life) {
		serialNum++;
		this.roundCount = 1;
		this.inputHistory =new HashSet<>();
		this.hangmanGame = new HangmanGame(serialNum,new Hangman(life));
	}

	public void start() {
		Hangman hangman = hangmanGame.getHangman();
		InputHelper.printInfo(hangmanGame.getGameId() + "번째 게임이 시작됩니다. 정답 단어는 " + hangman.getWordSize() + "글자 입니다.");

		while (hangman.getLife() > 0) {
			String alphabet=userGuess(hangman.toString());

			HangmanRound round =new HangmanRound(roundCount, hangman.getLife(), hangman.getHiddenWord().toString(),alphabet);
			hangmanGame.saveRound(round);

			List<Integer> replaceIndexes = findIndexes(hangman.getAnswer(), alphabet);

			// 추측 실패
			if (replaceIndexes.isEmpty()) {
				hangman.decrementLife();
				if (hangman.getLife() == 0) {
					break;    // 남은 목숨 없음. 게임 종료.
				}

				roundCount++;
				continue;
			}

			// 알파벳 추측 성공
			replaceIndexes.forEach(index -> hangman.replaceHiddenWord(index, alphabet));

			if (answerCheck(hangman.getAnswer(),hangman.getHiddenWord().toString())) {
				hangmanGame.setSuccess(true);
				printResult();
				return;    // 정답 맞춤. 게임 종료.
			}

			roundCount++;
		}
		hangmanGame.setSuccess(false);
		printResult();
	}

	private String userGuess(String hangmanStatus) {
		while (true){
			try {
				String alphabet = InputHelper.singleAlphabetInput(roundCount +" 라운드 : "+hangmanStatus);
				if(inputHistory.contains(alphabet)){
					throw new DuplicateTryException();
				}

				inputHistory.add(alphabet);
				return alphabet;
			}catch(DuplicateTryException e){
				InputHelper.printInfo(e.getMessage());
			}
		}
	}

	private List<Integer> findIndexes(String word, String alphabet) {
		List<Integer> indexList = new ArrayList<>();
		int index = word.indexOf(alphabet);

		while (index != -1) {
			indexList.add(index);
			index = word.indexOf(alphabet, index + 1);
		}

		return indexList;
	}

	private Boolean answerCheck(String answer, String hiddenWord){
		return answer.equals(hiddenWord);
	}

	public void printResult(){
		InputHelper.printInfo(hangmanGame.getSuccess() ? "축하합니다. 정답입니다.":"실패입니다. 다시 도전해보세요!");
		InputHelper.printInfo(hangmanGame.toString());
	}

}
