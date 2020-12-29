package telran.spring.service.impl;


import org.springframework.stereotype.Service;

import telran.spring.service.interfaces.Calculator;
@Service
public class CalculatorSipleImpl implements Calculator{

	@Override
	public int calculate(int val1, int val2, String operation) {
		int res = 0;
		switch (operation) {
			case ADD:{
				res = val1+val2;
				break;
			}
			case SUB:{
				res = val1-val2;
				break;
			}
			case DIV:{
				res = val1/val2;
				break;
			}
			case MUL:{
				res = val1*val2;
				break;
			}
			default: throw new RuntimeException("Unknown operation");
		}
		return res;
	}

}
