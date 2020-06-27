package TASKDATA3;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

//This class represents a Transaction
public class Transaction {
	// Transaction id
	private int id;
	// Transaction month
	private String month;
	// List of products in this transaction
	private LinkedList<String> products;

	public Transaction(int id) {
		this.id = id;
		products = new LinkedList<String>();
	}

	// Sort the products in the product list
	private void sortProducts() {
		// Sort the product list
		Collections.sort(products, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Collator.getInstance().compare(o1, o2);
			}
		});
	}

	// Add a product to the product list
	public void addProduct(String product) {
		if (!products.contains(product)) {
			// Add the products
			products.add(product);
			// Sort the product list
			sortProducts();
		}
	}

	// Remove a product from the product list
	public void removeProduct(String product) {
		products.remove(product);
	}

	// Get all the products in this transaction
	public LinkedList<String> getProducts() {
		return products;
	}

	// Get the transaction month
	public String getMonth() {
		return this.month;
	}

	// Set the transaction month
	public void setMonth(String month) {
		this.month = month;
	}

	// Return transaction ID
	public int getID() {
		return this.id;
	}
}
