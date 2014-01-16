package be.fabrice.testspring.aop.annotation;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations="classpath:/aop/annotation/test-aop-spring.xml")
public class TestAop extends AbstractTestNGSpringContextTests{
	@Autowired
	private SimpleBean simpleBean;
	
	@Test
	public void testProxiedAdd(){
		int response = simpleBean.add(6,7);
		assertEquals(response, -1);
		assertEquals(simpleBean.originalResponseWas(),13);
	}
	
	@Test
	public void testNonProxiedMult(){
		int response = simpleBean.mult(6,7);
		assertEquals(response, 42);
		assertEquals(simpleBean.originalResponseWas(),42);
	}
}
