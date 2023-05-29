package src.hangman;

import src.util.WordBook;

public class Hangman {
	private final String answer;
	private int wordSize;
	private StringBuilder hiddenWord;
	private int life;

	public Hangman(int life) {
		answer= WordBook.getRandom();
		wordSize=answer.length();

		hiddenWord=new StringBuilder();
		while(hiddenWord.length()<wordSize) {
			hiddenWord.append('_');
		}

		this.life=life;
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

	public void replaceHiddenWord(int index,String alphabet) {
		hiddenWord.replace(index,index+1,alphabet);
	}

	public void decrementLife() {
		this.life--;
	}

	@Override
	public String toString() {
		return hiddenWord+", 목숨 "+life;
	}
}
