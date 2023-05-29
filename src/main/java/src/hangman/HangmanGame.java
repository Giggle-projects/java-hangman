package src.hangman;

import java.util.SortedMap;
import java.util.TreeMap;

public class HangmanGame {
	private int gameId;
	private SortedMap<Integer,HangmanRound> roundHistory;
	private Hangman hangman;
	private Boolean success;

	public HangmanGame(int gameId, Hangman hangman) {
		this.gameId = gameId;
		this.roundHistory =new TreeMap<>();
		this.hangman = hangman;
		this.success = false;
	}

	public void saveRound(HangmanRound round){
		roundHistory.put(round.getRoundId(),round);
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
		roundHistory.forEach((key, value) ->sb.append(value.toString()));
		sb.append("===================\n");

		return sb.toString();
	}
}
