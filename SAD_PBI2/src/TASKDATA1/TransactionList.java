package TASKDATA1;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import TASKDATA4.Transaction;

public class TransactionList {
	
	private LinkedList<Transaction> transactionList; 	// Transaction list
	private LinkedList<String> productList;				// Product list
	private LinkedList<String> qtrList;					// Quarterly list
	
	//Constructor
	public TransactionList()
	{
		transactionList = new LinkedList<Transaction>();
		productList = new LinkedList<String>();
		qtrList = new LinkedList<String>();
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
	
	//Add a product to the product list
	public void addProduct(String product) 
	{
		if(!productList.contains(product)) 
		{
			productList.add(product);
			sortStringLinkedList(productList);
		}
	}
	
	//Check the existence from the transaction
	public boolean transactionExists(int transactionID)
	{
		for (int i=0; i < transactionList.size(); i++)
			if (transactionList.get(i).getID() == transactionID)
				return true;
		
		return false;
	}
	
	//Get the transaction from the list
	public int getTransactionIndex(int transactionID)
	{
		for (int i = 0; i < transactionList.size(); i++)
			if (transactionList.get(i).getID() == transactionID)
				return i;
		
		return -1;
	}
	
	//Add transaction from the list
	public void addTransaction(Transaction transaction) {
		if (transactionExists(transaction.getID())) 
		{
			int transactionIndex = getTransactionIndex(transaction.getID());
			
			for (int i = 0; i < transaction.getProducts().size(); i++)
			{
				transactionList.get(transactionIndex).addProduct(transaction.getProducts().get(i));
				addProduct(transaction.getProducts().get(i));
			}
		}
		else
		{
			transactionList.add(transaction);
			for (int i=0; i < transaction.getProducts().size(); i++)
			{
				addProduct(transaction.getProducts().get(i));
			}
		}
	}

	//Remove transaction from the list
	public void removeTransaction(Transaction transaction)
	{
		transactionList.remove(transaction);
	}
	
	public String toArff()
	{
		String arff = "";
		arff += "@relation TASKDATA1\n\n"; //The format for the arff TAKSDATA1
		arff += "@attribute TID {";
		
		//Add the transactions of possible values list
		for (int i = 0; i < transactionList.size(); i++)
			arff += transactionList.get(i).getID() + ",";
		
		//Remove last comma and close the transaction attribute
		arff = arff.substring(0, arff.length() - 1);
		arff += "}\n";
		
		//Add the products to the attribute list
		for (int i = 0; i < productList.size(); i++)
			arff += "@attribute " + productList.get(i) + "{1, 0}\n";
		
		//Add the data (transactions)
		arff += "\n@data\n";
		for (int i = 0; i < transactionList.size(); i++)
		{
			Transaction currentTransaction = transactionList.get(i);
			arff += currentTransaction.getID() + ",";
			
			//Set products
			for (int j = 0; j < productList.size(); j++)
			{
				if (currentTransaction.getProducts().contains(productList.get(j)))
					arff += "1, ";
				else 
					arff += "0, ";
			}
			arff = arff.substring(0, arff.length() - 1); //remove last comma
			arff += "\n";
		}
		
		return arff;
	}
}
