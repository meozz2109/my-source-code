package vnua.k62cnpm.xdptpm.libmanage.test.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private Calculator cal;
	
	@Before
	public void setUp() throws Exception {
		cal = new Calculator();
	}
	
	@After
	public void tearDown() throws Exception {
		cal = null;
	}
	
	@Test
	public void testAdd() {
		double res = cal.add(32.52, 42.24);
		assertEquals(74.76, res, 0);
	}

	@Test
	public void testSub() {
		double res = cal.substract(62.52, 42.24);
		assertEquals(20.28, res, 0);
	}
	
	@Test
	public void testMul() {
		double res = cal.multiple(12.52, 42.24);
		assertEquals(528.8448, res, 0);
	}

	@Test
	public void testDiv() {
		double res = cal.divide(21.12, 42.24);
		assertEquals(0.5, res, 0.001);
	}
	
	@Test(expected = ArithmeticException.class)
	public void testDivideByZero() {
		cal.divide(32.52, 0);
	}

	

}
