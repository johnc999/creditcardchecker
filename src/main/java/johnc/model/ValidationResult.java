package johnc.model;

public class ValidationResult {
	
	private String company;
	private String cardnumber;
	private String result;
	
	public ValidationResult() {
	}
	
	public ValidationResult(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public ValidationResult(String company, String cardnumber, String result) {
		this.company = company;
		this.cardnumber = cardnumber;
		this.result = result;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ValidationResult [company=" + company + ", cardnumber=" + cardnumber + ", result=" + result + "]";
	}
}
