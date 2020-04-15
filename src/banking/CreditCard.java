package banking;

public class CreditCard {

	private long personalCreditCardNumber;
	private long personalID;
	private int personalPIN;
	private int personalBalance;

	CreditCard(long number, int pin) {
		personalCreditCardNumber = number;
		personalPIN = pin;
		personalBalance = 0;
		personalID = number % 1_000_000_000;
	}

	@Deprecated
	CreditCard(int id, int pin) {
		personalCreditCardNumber = 4_000_000_000_000_000L + id;
		personalPIN = pin;
		personalBalance = 0;
		personalID = id;
	}

	CreditCard(long number, int pin, int balance) {
		personalCreditCardNumber = number;
		personalPIN = pin;
		personalBalance = balance;
	}

	public long getPersonalCreditCardNumber() {
		return personalCreditCardNumber;
	}

	public int getPersonalPIN() {
		return personalPIN;
	}

	@Deprecated
	public String getPersonalPINString() {
		int amountZero = 0;
		String pinStr = "";
		int temp = personalPIN;

		while (personalPIN > 0) {
			personalPIN /= 10;
			amountZero++;
		}
		personalPIN = temp;
		amountZero = 4 - amountZero;
		while (amountZero > 0) {
			pinStr += "0";
			amountZero--;
		}
		pinStr += Integer.toString(personalPIN);
		return pinStr;
	}


	public int getPersonalBalance() {
		return personalBalance;
	}
	public void setPersonalBalance(int personalBalance) {
		this.personalBalance = personalBalance;
	}
	public void changePersonalBalance(int amount) {
		personalBalance += amount;
	}

	public long getPersonalID() {
		return personalID;
	}
}
