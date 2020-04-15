package banking;

import java.sql.*;

public class DataBase {

	private static final String PATH = "/home/justspace/IdeaProjects/Simple Banking System/DataBase/";
	private static final String DRIVER = "jdbc:sqlite:";
	private static final String FILENAME = "card.s3db";
	private static final String TABLENAME = "card";
	private Connection connection;

	DataBase(String filePath) {
		try {
			if (filePath == null)
				connection = DriverManager.getConnection(DRIVER + PATH + FILENAME);
			else
				connection = DriverManager.getConnection(DRIVER + filePath);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createDB() {

		String createDB = "CREATE TABLE IF NOT EXISTS " + TABLENAME + "(\n" +
				"    " +
				"id      INTEGER,\n" +
				"    " +
				"number  TEXT,\n" +
				"    " +
				"pin     TEXT,\n" +
				"    " +
				"balance INTEGER DEFAULT 0);";
		try {
			connection.createStatement().execute(createDB);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void createUser(CreditCard card) {
		String create = "INSERT INTO " + TABLENAME + " (id, number, pin, balance) VALUES (\n" +
				card.getPersonalID() + ", " +
				card.getPersonalCreditCardNumber() + ", " +
				card.getPersonalPIN() + ", " +
				card.getPersonalBalance() +
				")";

		try {
			connection.createStatement().execute(create);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public CreditCard getUser(long creditCardNumber) {
		String get = "SELECT * FROM " + TABLENAME + " WHERE number = " + creditCardNumber;
		CreditCard res = null;

		try {
			ResultSet data = connection.createStatement().executeQuery(get);
			res = new CreditCard(creditCardNumber, data.getInt("pin"), data.getInt("balance"));
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}

	public void addIncome(long cardNumber, int amount) {
		String update = "UPDATE " + TABLENAME + " SET balance = balance + " + amount + " WHERE number = " + cardNumber;

		try {
			connection.createStatement().execute(update);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public int transfer (long userFrom, long userTo, int amount) {
		String getUserFrom = "SELECT * FROM " + TABLENAME + " WHERE number = " + userFrom;
		String update = "UPDATE " + TABLENAME + " SET balance = balance + " + amount + " WHERE number = " + userTo + ";" +
				"UPDATE " + TABLENAME + " SET balance = balance - " + amount + " WHERE number = " + userFrom;

		if (userFrom == userTo) {
			Printable.sameAccountTransferError();
			return -1;
		}

		try {
			ResultSet dataUserFrom = connection.createStatement().executeQuery(getUserFrom);
			if (!LuhnAlgorithm.luhnCheck(dataUserFrom.getString("number"))) {
				Printable.wrongTransferCardLuhnError();
				return -1;
			}
			if (amount > dataUserFrom.getInt("balance")) {
				Printable.moneyLimitError();
				return -1;
			}
			connection.createStatement().execute(update);

		} catch (SQLException e) {
			Printable.noSuchCardError();
		}
		return 1;
	}

	public void deleteUser(long cardNumber) {
		String delete = "DELETE FROM " + TABLENAME + " WHERE number = " + cardNumber;

		try {
			connection.createStatement().executeUpdate(delete);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void closeDB() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
