package TASKDATA1;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Transaction {

	private int id;							//Transaction of product id
	private String dealsize;				//Transaction Deal Size
	private int qtr; 						//Transaction QTR - Quarterly
	private LinkedList<String> products;	//List of products from a transaction 
	
	public Transaction(int id, String dealsize, int qtr)
	{
		this.id = id;
		this.dealsize = dealsize;
		this.qtr = qtr;
		products = new LinkedList<String>();
	}
	
	
	// Get the id transaction from the product
	public int getID()
	{
		return this.id;
	}
	
	// Get the deal size from the product
	public String getDealsize()
	{
		return this.dealsize;
	}
	
	// Set the transaction of Deal Size
	public void setDealsize(String dealsize)
	{
		this.dealsize = dealsize;
	}
	
	// Get the Quarterly from the product
	public int getQTR()
	{
		return this.qtr;
	}
	
	//Get all the products in the transaction
	public LinkedList<String> getProducts() 
	{
		return products;
	}
	
	
	
}
