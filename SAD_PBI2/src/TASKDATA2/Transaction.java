package TASKDATA2;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Transaction {
	
	private int id;
	private String productline;
	private String country;
	private LinkedList<String> products;
	
	public Transaction(int id ) //String productline, String country)
	{
		this.id = id;
		//this.productline = productline;
		//this.country = country;
		products = new LinkedList<String>();
	}
	
	// Sort the products in the product list
	private void sortProducts() {
		Collections.sort(products, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
				return Collator.getInstance().compare(o1, o2);
			}
		});
	}
	
	// Add the products in the product list
	public void addProduct(String product)
	{
		if(!products.contains(product)) 
		{
			//Add the product
			products.add(product);
			sortProducts(); // sort the product
		}
	}
		
	// Remove the products in the product list
	public void removeProduct(String product)
	{
		products.remove(product);
	}
	
	// Return transaction ID
		public int getID() {
			return this.id;
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
