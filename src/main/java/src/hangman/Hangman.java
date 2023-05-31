package src.hangman;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import src.util.WordBook;

public class Hangman {
	private static final Character HIDDEN_SPECIAL_CHARACTERS ='_';
	private final String answer;
	private int wordSize;
	private StringBuilder hiddenWord;
	private int life;
	private Set<String> guessedAlphabet;

	public Hangman(int life) {
		answer= WordBook.getRandom();
		wordSize=answer.length();

		hiddenWord=new StringBuilder();
		while(hiddenWord.length()<wordSize) {
			hiddenWord.append(HIDDEN_SPECIAL_CHARACTERS);
		}

		this.life=life;
		guessedAlphabet=new HashSet<>();
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

	public void guessUpdate(List<Integer> replaceIndexes,String alphabet) {
		guessedAlphabet.add(alphabet);
		replaceIndexes.forEach(
			index -> hiddenWord.replace(index,index+1,alphabet)
		);
	}

	public void decrementLife() {
		this.life--;
	}

	@Override
	public String toString() {
		return hiddenWord+", 목숨 "+life;
	}
}
