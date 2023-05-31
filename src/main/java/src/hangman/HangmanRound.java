package src.hangman;

public class HangmanRound {
	private int roundId;
	private int roundLife;
	private String roundBoard;
	private String roundInput;

	public HangmanRound(int roundId, int roundLife, String roundBoard, String roundInput) {
		this.roundId = roundId;
		this.roundLife = roundLife;
		this.roundBoard = roundBoard;
		this.roundInput = roundInput;
	}

	public int getRoundId() {
		return roundId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("라운드 id : ").append(roundId);
		sb.append(", 남은 목숨 : ").append(roundLife);
		sb.append(", ").append(roundBoard);
		sb.append(", 사용자 입력 : ").append(roundInput).append('\n');

		return sb.toString();
	}

	public String resultString(){
		StringBuilder sb = new StringBuilder();

		sb.append("\n=== Round Result ===\n");
		sb.append(this.toString());
		sb.append("===================\n");

		return sb.toString();
	}
}
