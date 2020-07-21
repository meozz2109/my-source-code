package vnua.k62cnpm.xdptpm.libmanage.test.application;

public class Calculator {
	public double add(double a, double b) {
		return a+b;
	}
	
	public double substract(double a, double b) {
		return a-b;
	}
	
	public double multiple(double a, double b) {
		return a*b;
	}
	
	public double divide(double a, double b) {
		if(b==0) {
			throw new ArithmeticException("Loi chia cho 0");
		}
		return a/b;
	}
}
