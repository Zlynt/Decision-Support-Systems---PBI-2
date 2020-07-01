package TASKDATA2;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TransactionList {
	
	private LinkedList<Transaction> transactionList; 		// Transaction list
	private LinkedList<String> productLineList;				// Product Line list
	private LinkedList<String> countryList;					// Quarterly list

	public TransactionList()
	{
		transactionList = new LinkedList<Transaction>();
		productLineList = new LinkedList<String>();
		countryList = new LinkedList<String>();
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
	
	public void addProductLine(String productLine)
	{
		if(!productLineList.contains(productLine))
		{
			productLineList.add(productLine);
			sortStringLinkedList(productLineList);
		}
	}
	
	public void addCountry(String country)
	{
		if(!countryList.contains(country))
		{
			countryList.add(country);
			sortStringLinkedList(countryList);
		}
	}
	
	// Check if a transaction already exists	
	public boolean transactionExists(int transactionID)
	{
		for (int i = 0; i < transactionList.size(); i++)
			if (transactionList.get(i).getID() == transactionID)
				return true;
		return false;
	}
	
	// Get a transaction position in the LinkedList
	public int getTransactionIndex(int transactionID) 
	{
		for (int i = 0; i < transactionList.size(); i++)
			if (transactionList.get(i).getID() == transactionID)
				return i;
		
		return -1;
	}
	
	// Add transaction to the list
	public void addTransaction(Transaction transaction) 
	{
		if (transactionExists(transaction.getID()))
		{
			int transactionIndex = getTransactionIndex(transaction.getID());
			
			// Add the products to the existing transaction
			for (int i = 0; i < transaction.getProducts().size(); i++)
			{
				transactionList.get(transactionIndex).addProduct(transaction.getProducts().get(i));
				addProductLine(transaction.getProducts().get(i));
			}
		}
		else
		{
			transactionList.add(transaction);
			addCountry(transaction.getCountry());
			for (int i = 0; i < transaction.getProducts().size(); i++)
			{
				addProductLine(transaction.getProducts().get(i));
			}
		}
	}

	// Remove transaction from the list
	public void removeTransaction(Transaction transaction) 
	{
		transactionList.remove(transaction);
	}
	
	public String toArff()
	{
		String arff = "";
		arff += "@relation TASKDATA2\n\n"; //The format for the arff TAKSDATA1
		arff += "@attribute TID {";
		arff += "}\n";
		
		return arff;
	}
}
