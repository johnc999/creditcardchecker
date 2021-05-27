package johnc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import johnc.model.ValidationResult;

@Service
public class ValidationServiceImpl implements ValidationService {
	
	public ValidationResult validateCredtCard(String cardnumber) {
		ValidationResult result = new ValidationResult(cardnumber);
		
		// sanity checks
		if (((cardnumber.startsWith("34")) || (cardnumber.startsWith("37"))) && (cardnumber.length() == 15)) {
			result.setCompany("AMEX");
		} else if ((cardnumber.startsWith("6011")) && (cardnumber.length() == 16)) {
			result.setCompany("Discover");
		} else if (((cardnumber.startsWith("51")) || (cardnumber.startsWith("52")) || (cardnumber.startsWith("53")) || 
			(cardnumber.startsWith("54")) || (cardnumber.startsWith("55"))) && (cardnumber.length() == 16)) {
			result.setCompany("Mastercard");
		} else if ((cardnumber.startsWith("4")) && ((cardnumber.length() == 13) || (cardnumber.length() == 16))) {
			result.setCompany("Visa");
		} else {
			result.setCompany("Unknown");
			result.setResult("invalid");
			return result;
		}
		
		// luhn algorithm check
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < cardnumber.length(); i++) {
		    if (Character.isDigit(cardnumber.charAt(i))) {
		    	numbers.add(Integer.parseInt("" + cardnumber.charAt(i)));
		    }   
		}
		int counter = 0;
		for (int i = numbers.size()-2; i>=0; i--) {
			if (counter % 2 == 0) {
			    Integer num = numbers.get(i);
			    numbers.set(i, num.intValue()*2);
			}
			counter++;
		}
		boolean done = false;
		while (!done) {
			boolean found = false;
			for (int i = 0; i < numbers.size(); i++) {
				if (numbers.get(i).intValue() > 9) {
					found = true;
					Integer firstnum = Integer.parseInt("" + numbers.get(i).toString().charAt(0));
					Integer secondnum = Integer.parseInt("" + numbers.get(i).toString().charAt(1));
					numbers.set(i, firstnum);
					numbers.add(i+1, secondnum);
				}
			}
			if (!found) {
				done = true;
			}
		}
		int total = 0;
		for (Integer num : numbers) {
			total += num;
		}
		if (total % 10 == 0) {
			result.setResult("valid");
		} else {
			result.setResult("invalid");
		}
		return result;
	}
}
