package src.hangman;

	import static src.util.Description.*;

	import java.util.ArrayList;
	import java.util.HashSet;
	import java.util.List;
	import java.util.Set;

	import src.exception.DuplicateTryException;
	import src.repository.GameRepository;
	import src.util.InputHelper;

public class GameLauncher {
	private static int gameSerialNum = 0;
	private static int roundSerialNum=0;
	private final GameRepository gameRepository = GameRepository.getInstance();
	private Set inputHistory;
	private HangmanGame hangmanGame;

	public GameLauncher(int life) {
		gameSerialNum++;
		this.inputHistory =new HashSet<>();
		this.hangmanGame = new HangmanGame(gameSerialNum,new Hangman(life));
		this.hangmanGame.setSuccess(false);
	}

	public void start() {
		gameRepository.save(hangmanGame);
		Hangman hangman = hangmanGame.getHangman();
		InputHelper.printInfo(String.format(FORMAT_INFO_GAME_ORDER_AND_WORD_SIZE,hangmanGame.getGameId(),hangman.getWordSize()));

		while (hangman.getLife() > 0) {
			roundSerialNum++;
			String alphabet=userGuess(hangman.toString());

			HangmanRound round =new HangmanRound(roundSerialNum, hangman.getLife(), hangman.getHiddenWord().toString(),alphabet);
			hangmanGame.saveRound(round);

			List<Integer> replaceIndexes = findIndexes(hangman.getAnswer(), alphabet);

			// 추측 실패
			if (replaceIndexes.isEmpty()) {
				hangman.decrementLife();
				if (hangman.getLife() == 0) {
					break;    // 남은 목숨 없음. 게임 종료.
				}

				continue;
			}

			// 알파벳 추측 성공
			hangman.guessUpdate(replaceIndexes, alphabet);

			if (answerCheck(hangman.getAnswer(),hangman.getHiddenWord().toString())) {
				hangmanGame.setSuccess(true);
				break;    // 정답 맞춤. 게임 종료.
			}
		}
		printResult();
	}

	private String userGuess(String hangmanStatus) {
		while (true){
			try {
				String alphabet = InputHelper.singleAlphabetInput(String.format(FORMAT_INPUT_GUESS_WITH_ROUND_INFO,roundSerialNum ,hangmanStatus));

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
		InputHelper.printInfo(hangmanGame.getSuccess() ? INFO_SUCCESS:INFO_FAIL);
		InputHelper.printInfo(hangmanGame.resultString());
	}

}
