package src.hangman;

import src.util.WordBook;

public class Hangman {
	private final String answer;
	private int wordSize;
	private StringBuilder hiddenWord;
	private int life;
	private int round;
	public Hangman(int life) {
		answer= WordBook.getRandom();
		wordSize=answer.length();

		hiddenWord=new StringBuilder();
		while(hiddenWord.length()<wordSize) {
			hiddenWord.append('_');
		}

		this.life=life;
		round=1;
	}

	public String getAnswer() {
		return answer;
	}

	public int getWordSize() {
		return wordSize;
	}

	public StringBuilder getHiddenWord() {
		return hiddenWord;
	}

	public int getLife() {
		return life;
	}

	public int getRound() {
		return round;
	}

	public void replaceHiddenWord(int index,String alphabet) {
		hiddenWord.replace(index,index+1,alphabet);
	}

	public void decrementLife() {
		this.life--;
	}

	public void incrementRound() {
		this.round++;
	}
}
