package banking;

import java.util.Random;

public class LuhnAlgorithm {

	private final static long START_NUMBER = 400_000_000_000_000L;
	private static Random rand = new Random();

	public static long luhnGenerate() {
		int     accountID = rand.nextInt(100_000_000);
		long    cardNumber = START_NUMBER + accountID;
		String  tempStr;
		int     tempInt;
		int     sum = 0;

		tempStr = Long.toString(cardNumber);
		for (int i = 0; i < 15; i++) {
			tempInt = tempStr.charAt(i) - '0';
			if (i % 2 == 0)
				tempInt *= 2;
			if (tempInt > 9)
				tempInt -= 9;
			sum += tempInt;
		}
		return cardNumber * 10 + (10 - sum % 10);
	}

	public static boolean luhnCheck(String cardNumber) {
		int result = 0;
		for (int i = 0; i < cardNumber.length(); i++) {
			int digit = Character.getNumericValue(cardNumber.charAt(i));
			if (i % 2 == 0) {
				int doubleDigit = digit * 2 > 9 ? digit * 2 - 9 : digit * 2;
				result += doubleDigit;
				continue;
			}
			result += digit;
		}
		return result % 10 == 0;
	}

	public static int pinGenerate() {
		return rand.nextInt(10_000);
	}
}
