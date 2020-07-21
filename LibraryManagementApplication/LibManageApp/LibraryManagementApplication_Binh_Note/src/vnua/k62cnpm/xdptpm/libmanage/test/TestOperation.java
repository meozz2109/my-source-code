package vnua.k62cnpm.xdptpm.libmanage.test;

import javax.management.openmbean.OpenMBeanOperationInfo;

import org.junit.Test;

import junit.framework.TestCase;
import vnua.k62cnpm.xdptpm.libmanage.test.application.Operation;

public class TestOperation extends TestCase{
	
	@Test
	public void testAdd() {
		Operation op = new Operation();
		int re = op.add(5, 5);
		assertEquals(re, 9);
	}
	
	@Test
	public void testSub() {
		Operation op = new Operation();
		int re = op.substract(1, 5);
		assertEquals(re, -1);
	}
	
}
