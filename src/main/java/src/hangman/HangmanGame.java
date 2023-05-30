package src.hangman;

import java.util.SortedSet;
import java.util.TreeSet;

import src.repository.RoundRepository;

public class HangmanGame {
	private final RoundRepository roundRepository= RoundRepository.getInstance();
	private int gameId;
	private SortedSet<Integer> roundIdSet;
	private Hangman hangman;
	private Boolean success;


	public HangmanGame(int gameId, Hangman hangman) {
		this.gameId = gameId;
		this.roundIdSet =new TreeSet<>();
		this.hangman = hangman;
		this.success = false;
	}

	public void saveRound(HangmanRound round){
		roundRepository.save(round);
		roundIdSet.add(round.getRoundId());
	}

	public int getGameId() {
		return gameId;
	}

	public Hangman getHangman() {
		return hangman;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
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
		roundIdSet.forEach(id ->sb.append(roundRepository.getByRoundId(id).toString()));
		sb.append("===================\n");

		return sb.toString();
	}
}
