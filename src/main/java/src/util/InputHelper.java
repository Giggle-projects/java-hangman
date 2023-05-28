package src.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputHelper {
	private static final StringBuilder OU = new StringBuilder("ou : ");
	private static final StringBuilder IN =  new StringBuilder("in : ");
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

	public static String singleInput(String message) {
		setBuilder();

		OU.append(message);
		System.out.println(OU);

		System.out.print(IN);
		String input = null;
		try {
			input = reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return input;
	}

	public static int singleIntegerInput(String message)  {
		setBuilder();

		OU.append(message);
		System.out.println(OU);

		System.out.print(IN);
		int input = 0;
		try {
			input = Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return input;
	}

	public static Integer[] multiIntegerInput(String message, String delim)  {
		setBuilder();

		OU.append(message);
		System.out.println(OU);

		System.out.print(IN);
		StringTokenizer tokenizer = null;
		try {
			tokenizer = new StringTokenizer(reader.readLine(), delim);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		int size=tokenizer.countTokens();
		Integer[] arr = new Integer[size];

		for(int i=0;i<size;i++){
			arr[i] = Integer.parseInt(tokenizer.nextToken());
		}

		return arr;
	}
}
