package TASKDATA1;

public class Tuple {

	private String productCode;
	private String dealSize;
	private String qtr_Id;

	public Tuple(String productCode, String dealSize, String qtr_Id) {

		this.productCode = productCode;
		this.dealSize = dealSize;
		this.qtr_Id = qtr_Id;
		
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDealSize() {
		return dealSize;
	}

	public void setDealSize(String dealSize) {
		this.dealSize = dealSize;
	}

	public String getQtr_Id() {
		return qtr_Id;
	}

	public void setQtr_Id(String qtr_Id) {
		this.qtr_Id = qtr_Id;
	}

}
