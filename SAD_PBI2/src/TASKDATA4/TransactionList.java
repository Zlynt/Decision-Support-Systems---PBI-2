package TASKDATA4;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TransactionList {
	// Transaction list
	private LinkedList<Transaction> transactionList;
	// Products present on the transactions
	private LinkedList<String> productList;

	// Constructor
	public TransactionList() {
		transactionList = new LinkedList<Transaction>();
		productList = new LinkedList<String>();
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

	// Add a product to the product list
	public void addProduct(String product) {
		if (!productList.contains(product)) {
			productList.add(product);
			sortStringLinkedList(productList);
		}
	}

	// Check if a transaction already exists
	public boolean transactionExists(int transactionID) {
		for (int i = 0; i < transactionList.size(); i++)
			if (transactionList.get(i).getID() == transactionID)
				return true;

		return false;
	}

	// Get a transaction position in the LinkedList
	public int getTransactionIndex(int transactionID) {
		for (int i = 0; i < transactionList.size(); i++)
			if (transactionList.get(i).getID() == transactionID)
				return i;

		return -1;
	}

	// Add transaction to the list
	public void addTransaction(Transaction transaction) {
		if (transactionExists(transaction.getID())) {
			int transactionIndex = getTransactionIndex(transaction.getID());

			// Add the products to the existing transaction
			for (int i = 0; i < transaction.getProducts().size(); i++) {
				transactionList.get(transactionIndex).addProduct(transaction.getProducts().get(i));
				addProduct(transaction.getProducts().get(i));
			}
		} else {
			transactionList.add(transaction);
			for (int i = 0; i < transaction.getProducts().size(); i++) {
				addProduct(transaction.getProducts().get(i));
			}
		}
	}

	// Remove transaction from the list
	public void removeTransaction(Transaction transaction) {
		transactionList.remove(transaction);
	}

	// Return the data has arff String
	public String toARFF() {
		String arff = "";

		arff += "@relation TASKDATA3\n\n"; // Add TaskData relation name (in this case it is the TaskData 3

		// Add the transaction id attribute
		//arff += "@attribute TID {";
		// Add the transactions to the possible values list
		//for (int i = 0; i < transactionList.size(); i++) {
		//	arff += transactionList.get(i).getID() + ",";
		//}
		// Remove last comma and close the transaction attribute
		//arff = arff.substring(0, arff.length() - 1);
		//arff += "}\n";

		// Add the products to the attribute list
		for (int i = 0; i < productList.size(); i++) {
			arff += "@attribute " + productList.get(i) + " {Comprou}\n";
		}

		// Add the data (transactions)
		arff += "\n@data\n";
		for (int i = 0; i < transactionList.size(); i++) {
			Transaction currentTransaction = transactionList.get(i);

			//arff += currentTransaction.getID() + ",";

			// Set the products
			for (int a = 0; a < productList.size(); a++) {
				if (currentTransaction.getProducts().contains(productList.get(a)))
					arff += "Comprou,";
				else
					arff += "?,";
			}
			// Remove extra comma
			arff = arff.substring(0, arff.length() - 1);
			arff += "\n";
		}

		return arff;
	}
}
