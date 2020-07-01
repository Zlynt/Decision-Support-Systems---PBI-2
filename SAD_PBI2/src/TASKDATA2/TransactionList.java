package TASKDATA2;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import TASKDATA4.Transaction;

public class TransactionList {
	
	private LinkedList<Transaction> transactionList; 		// Transaction list
	private LinkedList<String> productLineList;				// Product Line list
	private LinkedList<String> countryList;						// Quarterly list

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
	
	public String toArff()
	{
		String arff = "";
		arff += "@relation TASKDATA2\n\n"; //The format for the arff TAKSDATA1
		arff += "@attribute TID {";
		arff += "}\n";
		
		return arff;
	}
}
