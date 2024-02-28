package be.fabrice.testspring.postProcessor.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations="classpath:postProcessor/bean/test-beanPostProcessor-spring.xml")
class TestBeanPostProcessor {
	@Autowired
	private SimpleBean simpleBean;
	
	@Test
	void testSimpleBeanIsInjected(){
		String value = simpleBean.getValue();
		assertEquals("TOTO0", value);
	}
}
