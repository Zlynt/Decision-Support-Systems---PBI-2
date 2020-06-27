package TASKDATA3;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TransactionList {
	// Transaction list
	private LinkedList<Transaction> transactionList;
	// Products present on the transactions
	private LinkedList<String> productList;
	// Months present on the transactions
	private LinkedList<String> monthList;

	// Constructor
	public TransactionList() {
		transactionList = new LinkedList<Transaction>();
	}

	// Sort any LinkedList<String>
	private void sortStringLinkedList(LinkedList<String> list) {
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Collator.getInstance().compare(o1, o2);
			}
		});
	}

	// Add a month to the month list
	public void addMonth(String month) {
		if (!monthList.contains(month)) {
			monthList.add(month);
			sortStringLinkedList(monthList);
		}
	}

	// Add a product to the product list
	public void addProduct(String product) {
		if (!productList.contains(product)) {
			productList.add(product);
			sortStringLinkedList(productList);
		}
	}

	// Add transaction to the list
	public void addTransaction(Transaction transaction) {
		transactionList.add(transaction);
		addMonth(transaction.getMonth());
		for (int i = 0; i < transaction.getProducts().size(); i++) {
			addProduct(transaction.getProducts().get(i));
		}
	}

	// Remove transaction from the list
	public void removeTransaction(Transaction transaction) {
		transactionList.remove(transaction);
	}

	// Return the data has arff String
	public String toARFF() {
		String arff = "";

		arff += "TASKDATA3\n\n"; // Add TaskData relation name (in this case it is the TaskData 3

		// Add the transaction id attribute
		arff += "@attribute TID {";
		// Add the transactions to the possible values list
		for (int i = 0; i < transactionList.size(); i++) {
			arff += transactionList.get(i) + ",";
		}
		// Remove last comma and close the transaction attribute
		arff = arff.substring(0, arff.length() - 1);
		arff += "}\n";

		// Add the months to the attribute list
		for (int i = 0; i < monthList.size(); i++) {
			arff += "@attribute " + monthList.get(i) + " {y,n}\n";
		}

		// Add the products to the attribute list
		for (int i = 0; i < productList.size(); i++) {
			arff += "@attribute " + productList.get(i) + " {y,n}\n";
		}

		// Add the data (transactions)
		arff += "\n@data\n";
		for (int i = 0; i < transactionList.size(); i++) {
			Transaction currentTransaction = transactionList.get(i);

			arff += currentTransaction.getID() + ",";

			// Set the month
			for (int a = 0; a < monthList.size(); a++) {
				String currentMonth = monthList.get(a);
				if (currentMonth.contains(currentTransaction.getMonth()))
					arff += "y,";
				else
					arff += "n,";
			}

			// Set the products
			for (int a = 0; a < productList.size(); a++) {
				if (currentTransaction.getProducts().contains(productList.get(a)))
					arff += "y,";
				else
					arff += "n,";
			}
			// Remove extra comma
			arff = arff.substring(0, arff.length() - 1);
			arff += "\n";
		}

		return arff;
	}
}
