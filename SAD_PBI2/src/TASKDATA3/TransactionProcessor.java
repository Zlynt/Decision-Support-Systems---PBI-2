package TASKDATA3;

import java.util.LinkedList;

public class TransactionProcessor {
	private AttributeValueList monthList;
	private AttributeValueList productList;
	private LinkedList<String[]> purchases;

	public TransactionProcessor() {
		monthList = new AttributeValueList();
		productList = new AttributeValueList();
		purchases = new LinkedList<String[]>();
	}

	public void addPurchase(String month, String product) {
		String[] purchase = { month, product };
		purchases.add(purchase);
	}

	public LinkedList<String[]> getPurchases() {
		return purchases;
	}

	public void addMonth(String month) {
		if (!monthList.contains(month))
			monthList.add(month);
	}

	public void addProduct(String product) {
		if (!productList.contains(product))
			productList.add(product);
	}

	public LinkedList<String> getMonthList() {
		return monthList.getList();
	}

	public LinkedList<String> getProductList() {
		return productList.getList();
	}
}
