package be.fabrice.testspring.postProcessor.bean;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import be.fabrice.testspring.postProcessor.bean.SimpleBean;




@ContextConfiguration(locations="classpath:postProcessor/bean/test-beanPostProcessor-spring.xml")
public class TestBeanPostProcessor extends AbstractTestNGSpringContextTests{
	@Autowired
	private SimpleBean simpleBean;
	
	@Test
	public void testSimpleBeanIsInjected(){
		String value = simpleBean.getValue();
		assertEquals(value, "TOTO0");
	}
}
