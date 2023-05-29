package src.util;

import static src.exception.ErrorCode.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import src.exception.InvalidDelimiterException;
import src.exception.InputEmptyException;
import src.exception.InvalidInputFormatException;

public class InputHelper {
	private static final StringBuilder OU = new StringBuilder("ou : ");
	private static final StringBuilder IN =  new StringBuilder("in : ");
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static final String INPUT_DELIMITER=", ";	// 콤마(,)와 공백
	private static final String ALPHABET_PATTERN = "^[a-zA-Z]$";	// 알파벳 한 글자
	private static final String INVALID_DELIMITER_PATTERN = ".*[^a-zA-Z0-9"+INPUT_DELIMITER+"].*";	// 영문자, 숫자, INPUT_DELIMITER 이외의 특수문자
	private static final String ONLY_WHITESPACE_PATTERN = "\\s*";	// 공백으로만 이루어진 문자열

	private static void setBuilder(){
		OU.setLength(0);
		IN.setLength(0);

		OU.append("ou : ");
		IN.append("in : ");
	}
	public static void printInfo(String infoStr){
		setBuilder();

		OU.append(infoStr);
		System.out.println(OU);
	}

	public static String singleAlphabetInput(String message) {
		setBuilder();

		OU.append(message);
		System.out.println(OU);

		while (true){
			try {
				System.out.print(IN);
				String input = reader.readLine();

				if(input.matches(ONLY_WHITESPACE_PATTERN)){
					throw new InputEmptyException();
				}

				if(!input.matches(ALPHABET_PATTERN)){
					throw new InvalidInputFormatException(INVALID_INPUT_FORMAT_ALPHABET.getMessage());
				}

				return input;
			}catch (InputEmptyException e){
				printInfo(e.getMessage());
			}catch (InvalidInputFormatException e){
				printInfo(e.getMessage());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static int singleIntegerInput(String message)  {
		setBuilder();

		OU.append(message);
		System.out.println(OU);

		while (true){
			try {
				System.out.print(IN);
				String input = reader.readLine();

				if(input.matches(ONLY_WHITESPACE_PATTERN)){
					throw new InputEmptyException();
				}

				int parsedInput = Integer.parseInt(input);

				return parsedInput;
			}catch (InputEmptyException e){
				printInfo(e.getMessage());
			}catch (NumberFormatException e) {
				printInfo(INVALID_INPUT_FORMAT.getMessage());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Integer[] multiIntegerInput(String message)  {
		setBuilder();

		OU.append(message);
		System.out.println(OU);

		while (true){
			try {
				System.out.print(IN);
				String input = reader.readLine();

				if(input.matches(ONLY_WHITESPACE_PATTERN)){
					throw new InputEmptyException();
				}

				if(input.matches(INVALID_DELIMITER_PATTERN)){
					throw new InvalidDelimiterException(); // 구분자 입력 오류
				}

				StringTokenizer tokenizer = new StringTokenizer(input, INPUT_DELIMITER);
				int size=tokenizer.countTokens();
				Integer[] arr = new Integer[size];

				for(int i=0;i<size;i++){
					String token = tokenizer.nextToken();
					arr[i] = Integer.parseInt(token);	// NumberFormatException
				}

				return arr;
			}catch (InputEmptyException e){
				printInfo(e.getMessage());
			}catch (InvalidDelimiterException e) {
				printInfo(e.getMessage());
			}catch (NumberFormatException e) {
				printInfo(INVALID_INPUT_FORMAT.getMessage());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
