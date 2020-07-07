package TASKDATA2;

import java.util.LinkedList;

public class ListTuple {

	private LinkedList<Tuple> tupleList;
	private AttributeValueList productlineList;
	private AttributeValueList countryList;

	public ListTuple() {
		tupleList = new LinkedList<Tuple>();
		productlineList = new AttributeValueList();
		countryList = new AttributeValueList();
	}

	public void addTuple(String productline, String country) {
		tupleList.add(new Tuple(productline, country));
	}
	
	public LinkedList<Tuple> getTuple(){
		return this.tupleList;
	}

	public void addProductLine(String productline) {
		productlineList.add(productline);
	}

	public void addCountry(String country) {
		countryList.add(country);
	}

	
	public LinkedList<String> getProductLineList(){
		return this.productlineList.getList();
	}
	
	public LinkedList<String> getCountryList(){
		return this.countryList.getList();
	}

}
