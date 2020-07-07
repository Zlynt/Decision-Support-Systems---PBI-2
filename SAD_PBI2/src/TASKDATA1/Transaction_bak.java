package TASKDATA1;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Transaction_bak {

	private String productcode;				//Transaction of product id
	private String dealsize;				//Transaction Deal Size
	private int qtr; 						//Transaction QTR - Quarterly
	private LinkedList<String> products;	//List of products from a transaction 
	
	public Transaction_bak(String productcode, String dealsize, int qtr)
	{
		this.productcode = productcode;
		this.dealsize = dealsize;
		this.qtr = qtr;
		products = new LinkedList<String>();
	}
	
	// Sort the products in the product list
	private void sortProducts() 
	{
		// Sort the product list
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
	
	// Get the id transaction from the product
	public String getProductCode()
	{
		return this.productcode;
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
