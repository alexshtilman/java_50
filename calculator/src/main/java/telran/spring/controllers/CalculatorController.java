package telran.spring.controllers;

import static telran.spring.api.ApiConstants.CALCULATOR_ADD;
import static telran.spring.api.ApiConstants.CALCULATOR_DIV;
import static telran.spring.api.ApiConstants.CALCULATOR_MUL;
import static telran.spring.api.ApiConstants.CALCULATOR_SUB;
import static telran.spring.api.ApiConstants.FIRST_PARAM;
import static telran.spring.api.ApiConstants.SECOND_PARAM;
import static telran.spring.service.interfaces.Calculator.ADD;
import static telran.spring.service.interfaces.Calculator.DIV;
import static telran.spring.service.interfaces.Calculator.MUL;
import static telran.spring.service.interfaces.Calculator.SUB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.spring.service.interfaces.Calculator;

@RestController
public class CalculatorController {

	@Autowired
	Calculator calculator;

	@GetMapping(CALCULATOR_ADD)
	ResponseEntity<?> add(@RequestParam(name = FIRST_PARAM, required = true) int val1,
			@RequestParam(name = SECOND_PARAM, defaultValue = "0") int val2) {
		return getResponce(val1, val2, ADD);
	}

	private ResponseEntity<?> getResponce(int val1, int val2, String operation) {
		try {
			return ResponseEntity.ok(calculator.calculate(val1, val2, operation));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(CALCULATOR_SUB)
	ResponseEntity<?> sub(@RequestParam(name = FIRST_PARAM, required = true) int val1,
			@RequestParam(name = SECOND_PARAM, defaultValue = "0") int val2) {
		return getResponce(val1, val2, SUB);
	}

	@GetMapping(CALCULATOR_MUL)
	ResponseEntity<?> mul(@RequestParam(name = FIRST_PARAM, required = true) int val1,
			@RequestParam(name = SECOND_PARAM, defaultValue = "1") int val2) {
		return getResponce(val1, val2, MUL);
	}

	@GetMapping(CALCULATOR_DIV)
	ResponseEntity<?> div(@RequestParam(name = FIRST_PARAM, required = true) int val1,
			@RequestParam(name = SECOND_PARAM, defaultValue = "1") int val2) {
		return getResponce(val1, val2, DIV);
	}

}
