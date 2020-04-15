package banking;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DataBase db;

		if (args.length > 1) {
			if (args[0].equals("-fileName"))
				db = new DataBase(args[1]);
			else {
				Printable.wrongCommandArguments();
				return;
			}
		}
		else
			db = new DataBase(null);
		db.createDB();
		byte input = -1;
		while (input != 0) {
			Printable.printStartMenu();
			input = scan.nextByte();
			switch (input) {
				case 1:
					long personalCreditCardNumber = LuhnAlgorithm.luhnGenerate();
					int pin = LuhnAlgorithm.pinGenerate();
					db.createUser(new CreditCard(personalCreditCardNumber, pin));
					Printable.cardCreated(personalCreditCardNumber, pin);
					break;
				case 2:
					Printable.enterCardNumber();
					long enteredCreditCardNumber = scan.nextLong();
					Printable.enterPin();
					int enteredPin = scan.nextInt();
					CreditCard personalCreditCard = db.getUser(enteredCreditCardNumber);
					if (personalCreditCard == null || personalCreditCard.getPersonalPIN() != enteredPin) {
						Printable.wrongCardOrPin();
						break;
					}
					Printable.successfulLogin();
					PersonalAccount account = new PersonalAccount(personalCreditCard, db);
					account.processingWork(scan);
					break;
				case 0:
					Printable.exit();
					db.closeDB();
					break;
			}
		}
	}
}
