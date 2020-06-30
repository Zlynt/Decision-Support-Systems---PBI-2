package TASKDATA2;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Transaction {
	
	private String productline;
	private String country;
	private LinkedList<String> products;
	
	public Transaction(String productline, String country)
	{
		this.productline = productline;
		this.country = country;
		products = new LinkedList<String>();
	}
	
	private void sortProducts() {
		// Sort the product list
		Collections.sort(products, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
				return Collator.getInstance().compare(o1, o2);
			}
		});
	}
	
	
	//Get the product line of the transaction
	public String getProductLine()
	{
		return this.productline;
	}
	
	//Set the product line of the transaction
	public void setProductLine(String productline)
	{
		this.productline = productline;
	}
	
	//Get the country of the transaction
	public String getCountry()
	{
		return this.country;
	}
	
	//Set the country of the transaction
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	// Get all the products in this transaction
	public LinkedList<String> getProducts() 
	{
		return products;
	}



}
