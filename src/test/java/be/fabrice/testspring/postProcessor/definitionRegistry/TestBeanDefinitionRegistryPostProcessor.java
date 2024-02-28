package be.fabrice.testspring.postProcessor.definitionRegistry;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



class TestBeanDefinitionRegistryPostProcessor {

	@Test
	void testIncorrectConfigurationIsPrototype(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:postProcessor/definitionRegistry/test-withoutDefinitionPostProcessor-spring.xml");
		SuspectedIncorrectService service1 = (SuspectedIncorrectService) applicationContext.getBean("suspectedIncorrectService");
		SuspectedIncorrectService service2 = (SuspectedIncorrectService) applicationContext.getBean("suspectedIncorrectService");
		
		assertNotSame(service1, service2, "Ce sont des prototypes");
		applicationContext.close();
	}

	@Test
	void testIncorrectConfigurationIsCorrectedByPostProcessor(){
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:postProcessor/definitionRegistry/test-definitionPostProcessor-spring.xml");
		SuspectedIncorrectService service1 = (SuspectedIncorrectService) applicationContext.getBean("suspectedIncorrectService");
		SuspectedIncorrectService service2 = (SuspectedIncorrectService) applicationContext.getBean("suspectedIncorrectService");
		
		assertSame(service1, service2, "Ce sont des singletons");
		applicationContext.close();
	}
}
