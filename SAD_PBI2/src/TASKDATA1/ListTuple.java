package TASKDATA1;

import java.util.LinkedList;

public class ListTuple {

	private LinkedList<Tuple> tupleList;
	private AttributeValueList productcodeList;
	private AttributeValueList dealsizeList;
	private AttributeValueList qtrList;

	public ListTuple() {
		tupleList = new LinkedList<Tuple>();
		productcodeList = new AttributeValueList();
		dealsizeList = new AttributeValueList();
		qtrList = new AttributeValueList();
	}

	public void addTuple(String productcode, String dealsize, String qtr_id) {
		tupleList.add(new Tuple(productcode, dealsize, qtr_id));
	}
	
	public LinkedList<Tuple> getTuple(){
		return this.tupleList;
	}

	public void addProductCode(String productcode) {
		productcodeList.add(productcode);
	}

	public void addDealSize(String dealsize) {
		dealsizeList.add(dealsize);
	}

	public void addQtrID(String qtr_id) {
		qtrList.add(qtr_id);
	}
	
	public LinkedList<String> getProductCodeList(){
		return this.productcodeList.getList();
	}
	
	public LinkedList<String> getDealSizeList(){
		return this.dealsizeList.getList();
	}
	
	public LinkedList<String> getQtrIDList(){
		return this.qtrList.getList();
	}

}
