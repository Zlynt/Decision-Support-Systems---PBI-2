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
	
	public String toArff()
	{
		String arff = "";
		arff += "@relation TASKDATA2\n\n"; //The format for the arff TAKSDATA1
		arff += "@attribute TID {";
		arff += "}\n";
		
		return arff;
	}
}
