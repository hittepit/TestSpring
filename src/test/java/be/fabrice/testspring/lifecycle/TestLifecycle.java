package be.fabrice.testspring.lifecycle;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class TestLifecycle {
	@Test
	public void testSingletonLifecycle(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/lifecycle/test-lifecycle-spring.xml");
		SingletonBean singletonBean = applicationContext.getBean(SingletonBean.class);
		assertNotNull(singletonBean);
		List<String> operations = singletonBean.getOperations();
		assertEquals(operations.size(), 3);
		assertEquals(operations.get(0),"constructor","First, object instanciation");
		assertEquals(operations.get(1),"injection","Second, injection");
		assertEquals(operations.get(2),"init","Thid, initialization with int or postConstruct methods");
		applicationContext.close();
		operations = singletonBean.getOperations();
		assertEquals(operations.size(), 4);
		assertEquals(operations.get(3),"destroy","Finaly, destroy witj predestroy method");
	}
	
	@Test
	public void testPrototypeLifecycle(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/lifecycle/test-lifecycle-spring.xml");
		PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
		assertNotNull(prototypeBean);
		List<String> operations = prototypeBean.getOperations();
		assertEquals(operations.size(), 3);
		assertEquals(operations.get(0),"constructor","First, object instanciation");
		assertEquals(operations.get(1),"injection","Second, injection");
		assertEquals(operations.get(2),"init","Thid, initialization with int or postConstruct methods");
		applicationContext.close();
		operations = prototypeBean.getOperations();
		assertEquals(operations.size(), 3, "Destroy is never called by spring on prototypes");
	}
}
