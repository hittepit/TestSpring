package be.fabrice.testspring.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.fabrice.testspring.properties.correct.ValuePropertyInjectionBean;

class TestPropertiesInjection {

	@Test
	void testPropertiesInjected(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:properties/test-classic-properties-spring.xml");
		ClassicalPropertyInjectionBean bean = (ClassicalPropertyInjectionBean) applicationContext.getBean("classic");
		
		assertEquals("toto", bean.getName());
		assertEquals(77, bean.getAge());
		applicationContext.close();
	}
	
	@Test
	void testUnknownPlaceholderThrowsExceptionAtStartup(){
		assertThrows(BeansException.class,
				() -> new ClassPathXmlApplicationContext("classpath:properties/test-classic-unknownproperties-spring.xml"));
	}
	
	@Test
	void testValuesInjected(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:properties/test-values-properties-spring.xml");
		ValuePropertyInjectionBean bean = (ValuePropertyInjectionBean) applicationContext.getBean("valueBean");
		
		assertEquals("toto", bean.getName());
		assertEquals(77, bean.getAge());
		applicationContext.close();
	}
	
	@Test
	void testIncorrectValuesInjectedThrowsException(){
		assertThrows(BeansException.class,
				() -> new ClassPathXmlApplicationContext("classpath:properties/test-incorrect-values-properties-spring.xml"));
	}
}
