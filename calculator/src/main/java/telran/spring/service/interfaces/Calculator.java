package telran.spring.service.interfaces;

public interface Calculator {
	int calculate(int val1, int val2, String operation);
	String ADD = "add";
	String SUB = "subscract";
	String MUL = "mulitply";
	String DIV = "divide";
}
