package be.fabrice.testspring.postProcessor;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import be.fabrice.testspring.postProcessor.factory.SimpleBean;



@ContextConfiguration(locations="classpath:postProcessor/test-spring.xml")
public class TestFactoryBeanPostProcessor extends AbstractTestNGSpringContextTests{
	@Autowired
	private SimpleBean simpleBean;
	
	@Test
	public void testSimpleBeanIsInjected(){
		String value = simpleBean.getValue();
		assertEquals(value, "toto");
	}
}
