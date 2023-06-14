package src.util;

import static src.exception.ErrorCode.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import src.exception.InputEmptyException;
import src.exception.InvalidDelimiterException;
import src.exception.InvalidInputFormatException;

public class InputHelper {
	private static final StringBuilder OU = new StringBuilder("ou : ");
	private static final StringBuilder IN = new StringBuilder("in : ");
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static final String INPUT_DELIMITER = ", ";    // 콤마(,)와 공백
	private static final String ALPHABET_PATTERN = "^[a-zA-Z]$";    // 알파벳 한 글자
	private static final String INVALID_DELIMITER_PATTERN =
		".*[^a-zA-Z0-9" + INPUT_DELIMITER + "].*";    // 영문자, 숫자, INPUT_DELIMITER 이외의 특수문자
	private static final String ONLY_WHITESPACE_PATTERN = "\\s*";    // 공백으로만 이루어진 문자열

	private static void refreshBuilder() {
		OU.setLength(0);
		IN.setLength(0);

		OU.append("ou : ");
		IN.append("in : ");
	}

	public static void printInfo(String infoStr) {
		refreshBuilder();

		OU.append(infoStr);
		System.out.println(OU);
	}

	public static String singleAlphabetInput(String message) {
		printInfo(message);

		while (true) {
			try {
				System.out.print(IN);
				String input = reader.readLine();

				if (input.matches(ONLY_WHITESPACE_PATTERN)) {
					throw new InputEmptyException();
				}

				if (!input.matches(ALPHABET_PATTERN)) {
					throw new InvalidInputFormatException(ERROR_INVALID_INPUT_FORMAT_ALPHABET.getMessage());
				}

				return input.toLowerCase();    // 입력 대소문자 가능 -> 내부에서는 소문자로 연산
			} catch (InputEmptyException | InvalidInputFormatException e) {
				printInfo(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static int singleIntegerInput(String message) {
		printInfo(message);

		while (true) {
			try {
				System.out.print(IN);
				String input = reader.readLine();

				if (input.matches(ONLY_WHITESPACE_PATTERN)) {
					throw new InputEmptyException();
				}

				return Integer.parseInt(input);
			} catch (InputEmptyException e) {
				printInfo(e.getMessage());
			} catch (NumberFormatException e) {
				printInfo(ERROR_INVALID_INPUT_FORMAT.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Integer[] multiIntegerInput(String message) {
		printInfo(message);

		while (true) {
			try {
				System.out.print(IN);
				String input = reader.readLine();

				if (input.matches(ONLY_WHITESPACE_PATTERN)) {
					throw new InputEmptyException();
				}

				if (input.matches(INVALID_DELIMITER_PATTERN)) {
					throw new InvalidDelimiterException(); // 구분자 입력 오류
				}

				StringTokenizer tokenizer = new StringTokenizer(input, INPUT_DELIMITER);
				int size = tokenizer.countTokens();
				Integer[] arr = new Integer[size];

				for (int i = 0; i < size; i++) {
					String token = tokenizer.nextToken();
					arr[i] = Integer.parseInt(token);    // NumberFormatException
				}

				return arr;
			} catch (InputEmptyException | InvalidDelimiterException e) {
				printInfo(e.getMessage());
			} catch (NumberFormatException e) {
				printInfo(ERROR_INVALID_INPUT_FORMAT.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close() {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
