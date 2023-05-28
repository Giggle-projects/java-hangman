package src.util;

import java.util.Random;

public enum WordBook {
	APPLE, HANGMAN, JAVA, SPRING;

	public static String getRandom(){
		return values()[new Random().nextInt(WordBook.values().length)].name();
	}
}
