package be.fabrice.testspring.postProcessor;

import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import be.fabrice.testspring.postProcessor.definitionRegistry.SuspectedIncorrectService;

public class TestBeanDefinitionRegistryPostProcessor {

	@Test
	public void testIncorrectConfigurationIsPrototype(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:postProcessor/test-withoutDefinitionPostProcessor-spring.xml");
		SuspectedIncorrectService service1 = (SuspectedIncorrectService) applicationContext.getBean("suspectedIncorrectService");
		SuspectedIncorrectService service2 = (SuspectedIncorrectService) applicationContext.getBean("suspectedIncorrectService");
		
		assertNotSame(service1, service2, "Ce sont des prototypes");
		applicationContext.close();
	}

	@Test
	public void testIncorrectConfigurationIsCorrectedByPostProcessor(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:postProcessor/test-definitionPostProcessor-spring.xml");
		SuspectedIncorrectService service1 = (SuspectedIncorrectService) applicationContext.getBean("suspectedIncorrectService");
		SuspectedIncorrectService service2 = (SuspectedIncorrectService) applicationContext.getBean("suspectedIncorrectService");
		
		assertSame(service1, service2, "Ce sont des singletons");
		applicationContext.close();
	}
}
