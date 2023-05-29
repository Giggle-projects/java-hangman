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

	@Override
	public String toString() {
		return "라운드 id : " + roundId +
			", 남은 목숨 : " + roundLife +
			", " + roundBoard +
			", 사용자 입력 : " + roundInput + '\n' ;
	}
}
