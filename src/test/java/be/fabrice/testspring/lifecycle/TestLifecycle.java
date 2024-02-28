package be.fabrice.testspring.lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


class TestLifecycle {
	@Test
	void testSingletonLifecycle(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/lifecycle/test-lifecycle-spring.xml");
		SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);
		assertNotNull(singletonBean);
		List<String> operations = singletonBean.getOperations();
		assertEquals(operations.size(), 3);
		assertEquals("constructor", operations.get(0),"First, object instanciation");
		assertEquals("injection",operations.get(1),"Second, injection");
		assertEquals("init",operations.get(2),"Third, initialization with int or postConstruct methods");
		applicationContext.close();
		operations = singletonBean.getOperations();
		assertEquals(4, operations.size());
		assertEquals("destroy", operations.get(3),"Finaly, destroy witj predestroy method");
	}
	
	@Test
	void testPrototypeLifecycle(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/lifecycle/test-lifecycle-spring.xml");
		PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
		assertNotNull(prototypeBean);
		List<String> operations = prototypeBean.getOperations();
		assertEquals(operations.size(), 3);
		assertEquals("constructor", operations.get(0),"First, object instanciation");
		assertEquals("injection", operations.get(1),"Second, injection");
		assertEquals("init", operations.get(2),"Thid, initialization with int or postConstruct methods");
		applicationContext.close();
		operations = prototypeBean.getOperations();
		assertEquals(3, operations.size(), "Destroy is never called by spring on prototypes");
	}
}
