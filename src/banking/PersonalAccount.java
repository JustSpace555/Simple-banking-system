package banking;

import java.util.Scanner;

public class PersonalAccount {

	private CreditCard personalCard;
	private DataBase db;

	PersonalAccount(CreditCard personalCard, DataBase db) {
		this.personalCard = personalCard;
		this.db = db;
	}

	public void processingWork(Scanner scan) {
		byte input;

		for (;;){
			Printable.printAccountMenu();
			input = scan.nextByte();
			switch (input) {
				case 1:
					Printable.printBalance(personalCard.getPersonalBalance());
					break;

				case 2:
					Printable.enterIncomingAmount();
					int income = scan.nextInt();
					db.addIncome(personalCard.getPersonalCreditCardNumber(), income);
					personalCard.changePersonalBalance(income);
					break;

				case 3:
					Printable.enterCardNumber();
					long enteredCard = scan.nextLong();
					Printable.enterTransferAmount();
					int transfer = scan.nextInt();
					if (db.transfer(personalCard.getPersonalCreditCardNumber(), enteredCard, transfer) == 1)
						personalCard.changePersonalBalance(-transfer);
					break;

				case 4:
					Printable.enterConfirmation();
					if (scan.nextLine().toUpperCase().equals("YES")) {
						db.deleteUser(personalCard.getPersonalCreditCardNumber());
						Printable.userDeleted();
						return;
					}
					break;

				case 5:
					Printable.logOut();
					return;

				case 0:
					Printable.exit();
					db.closeDB();
					System.exit(0);

				default:
					Printable.wrongCommand();
					break;
			}
		}
	}
}
