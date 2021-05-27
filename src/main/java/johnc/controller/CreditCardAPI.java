package johnc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http. ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import johnc.model.ValidationResult;
import johnc.service.ValidationService;

@RestController
@RequestMapping("/creditcard")
public class CreditCardAPI {
	
	@Autowired
	ValidationService validationService;
	
	@GetMapping("/validate/{cardnumber}")
	public ResponseEntity<ValidationResult> getValidationResult(@PathVariable String cardnumber) {
		try {

			if ((cardnumber == null) || (cardnumber.isEmpty())) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			ValidationResult validationResult = validationService.validateCredtCard(cardnumber);

			return new ResponseEntity<>(validationResult, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
