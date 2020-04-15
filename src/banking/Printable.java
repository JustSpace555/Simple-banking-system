package banking;
public class Printable {

	static void printStartMenu() {
		System.out.println("1. Create an account");
		System.out.println("2. Log into account");
		System.out.println("0. Exit\n");
	}

	static void cardCreated(long cardNumber, int pin) {
		System.out.println("Your card have been created");
		System.out.println("Your card number:");
		System.out.println(cardNumber);
		System.out.format("Your card PIN:\n%04d\n\n", pin);
	}

	static void enterCardNumber() {
		System.out.println("Please, enter card number:");
	}

	static void enterPin() {
		System.out.println("Enter your PIN:");
	}

	static void wrongCardOrPin() {
		System.out.println("Wrong card number of PIN!\n");
	}

	static void successfulLogin() {
		System.out.println("You have successfully logged in!\n");
	}

	static void printAccountMenu() {
		System.out.println("1. Balance");
		System.out.println("2. Add income");
		System.out.println("3. Do transfer");
		System.out.println("4. Close account");
		System.out.println("5. Log out");
		System.out.println("0. Exit");
	}

	static void printBalance(int balance) {
		System.out.format("Balance: %d\n", balance);
	}

	static void logOut() {
		System.out.println("\nYou have successfully logged out!\n");
	}

	static void wrongCommand() {
		System.out.println("Wrong command!\n");
	}

	static void exit() {
		System.out.println("Bye!");
	}

	static void wrongCommandArguments() {
		System.out.println("Wrong command arguments\n");
	}

	static void sameAccountTransferError() {
		System.out.println("You can't transfer money to the same account!");
	}

	static void wrongTransferCardLuhnError() {
		System.out.println("Probably you made mistake in card number. Please try again!");
	}

	static void noSuchCardError() {
		System.out.println("Such a card does not exist.");
	}

	static void moneyLimitError() {
		System.out.println("You don't have these amount of money.");
	}

	static void enterTransferAmount() {
		System.out.println("Please, enter amount of money to transfer.");
	}

	static void enterIncomingAmount() {
		System.out.println("Please, enter amount of incoming money.");
	}

	static void enterConfirmation() {
		System.out.println("Are you sure? Enter YES/NO");
	}

	static void userDeleted() {
		System.out.println("Your account was successfully deleted.\n");
	}
}
