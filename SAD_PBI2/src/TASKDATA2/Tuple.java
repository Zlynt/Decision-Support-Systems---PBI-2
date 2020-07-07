package TASKDATA2;

public class Tuple {

	private String productLine;
	private String country;

	public Tuple(String productLine, String country) {

		this.setProductLine(productLine);
		this.setCountry(country);
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
