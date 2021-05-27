package johnc.service;

import johnc.model.ValidationResult;

public interface ValidationService {
	
	public ValidationResult validateCredtCard(String cardnumber);

}
