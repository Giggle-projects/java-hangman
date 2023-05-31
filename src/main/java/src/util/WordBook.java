package src.util;

import java.util.Random;

public enum WordBook {
	ADVENTURE, APPLE, BASEBALL, BUTTERFLY, CHOCOLATE,
	COMPUTER, ELEPHANT, FIREWORKS, GUITAR, HANGMAN,
	JAVA, MOUNTAIN, RAINBOW, SPRING, SUNSHINE;

	public static String getRandom() {
		int randomIndex = new Random().nextInt(WordBook.values().length);
		return values()[randomIndex].name().toLowerCase();
	}
}
