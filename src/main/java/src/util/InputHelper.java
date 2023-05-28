package src.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputHelper {
	private static final StringBuilder OU = new StringBuilder("ou : ");
	private static final StringBuilder IN =  new StringBuilder("in : ");
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void printInfo(String infoStr){
		OU.append(infoStr);
		System.out.println(OU);
	}

	public static String singleInput(String message) throws IOException {
		OU.append(message);
		System.out.println(OU);

		System.out.print(IN);
		String input = reader.readLine();

		return input;
	}

	public static int singleIntegerInput(String message) throws IOException {
		OU.append(message);
		System.out.println(OU);

		System.out.print(IN);
		int input = Integer.parseInt(reader.readLine());

		return input;
	}

	public static String[] multiInput(String message, String delim) throws IOException {
		OU.append(message);
		System.out.println(OU);

		System.out.print(IN);
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), delim);

		int size=tokenizer.countTokens();
		String[] arr = new String[size];

		for(int i=0;i<size;i++){
			arr[i] = tokenizer.nextToken();
		}

		return arr;
	}
}
