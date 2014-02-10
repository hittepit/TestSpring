package be.fabrice.testspring.properties;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import be.fabrice.testspring.properties.correct.ValuePropertyInjectionBean;

public class TestPropertiesInjection {

	@Test
	public void testPropertiesInjected(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:properties/test-classic-properties-spring.xml");
		ClassicalPropertyInjectionBean bean = (ClassicalPropertyInjectionBean) applicationContext.getBean("classic");
		
		assertEquals(bean.getName(), "toto");
		assertEquals(bean.getAge(),77);
		applicationContext.close();
	}
	
	@Test(expectedExceptions=BeansException.class)
	public void testUnknownPlaceholderThrowsExceptionAtStartup(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:properties/test-classic-unknownproperties-spring.xml");
	}
	
	@Test
	public void testValuesInjected(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:properties/test-values-properties-spring.xml");
		ValuePropertyInjectionBean bean = (ValuePropertyInjectionBean) applicationContext.getBean("valueBean");
		
		assertEquals(bean.getName(), "toto");
		assertEquals(bean.getAge(),77);
		applicationContext.close();
	}
	
	@Test(expectedExceptions=BeansException.class)
	public void testIncorrectValuesInjectedThrowsException(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:properties/test-incorrect-values-properties-spring.xml");
	}
}
