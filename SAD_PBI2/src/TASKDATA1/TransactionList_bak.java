package TASKDATA1;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class TransactionList_bak {
	
	private LinkedList<Transaction_bak> transactionList; 	// Transaction list
	private LinkedList<String> productList;				// Product list
	private LinkedList<String> dealsizeList;			// Deal Size list
	private LinkedList<String> qtrList;					// Quarterly list
	
	//Constructor
	public TransactionList_bak()
	{
		transactionList = new LinkedList<Transaction_bak>();
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
	
	public void addDealsize(String dealsize)
	{
		if(!dealsizeList.contains(dealsize))
		{
			dealsizeList.add(dealsize);
			sortStringLinkedList(dealsizeList);
		}
	}
	
	public void addQTR(String qtr)
	{
		if(!qtrList.contains(qtr))
		{
			qtrList.add(qtr);
			sortStringLinkedList(qtrList);
		}
	}
	
	//Check the existence from the transaction
	public boolean transactionExists(String transactionID)
	{
		for (int i=0; i < transactionList.size(); i++)
			if (transactionList.get(i).getProductCode() == transactionID)
				return true;
		
		return false;
	}
	
	//Get the transaction from the list
	public int getTransactionIndex(String transactionID)
	{
		for (int i = 0; i < transactionList.size(); i++)
			if (transactionList.get(i).getProductCode() == transactionID)
				return i;
		
		return -1;
	}
	
	//Add transaction from the list
	public void addTransaction(Transaction_bak transaction) {
		if (transactionExists(transaction.getProductCode())) 
		{
			int transactionIndex = getTransactionIndex(transaction.getProductCode());
			
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
	public void removeTransaction(Transaction_bak transaction)
	{
		transactionList.remove(transaction);
	}
	
	public String toARFF()
	{
		String arff = "";
		arff += "@relation TASKDATA1\n\n"; //The format for the arff TAKSDATA1
		arff += "@attribute TID {";
		
		//Add the transactions of possible values list
		for (int i = 0; i < transactionList.size(); i++)
			arff += transactionList.get(i).getProductCode() + ",";
		
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
			Transaction_bak currentTransaction = transactionList.get(i);
			arff += currentTransaction.getProductCode() + ",";
			
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
