package be.fabrice.testspring.postProcessor.factory;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:postProcessor/factory/test-factoryPostProcessor-spring.xml")
class TestRuleBeanFactoryPostProcessor {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	void testBadlyConfiguredRuleBecameCorrect(){
		BadlyConfiguredRule rule1 = (BadlyConfiguredRule) applicationContext.getBean("badlyConfiguredRule");
		BadlyConfiguredRule rule2 = (BadlyConfiguredRule) applicationContext.getBean("badlyConfiguredRule");
		
		assertNotSame(rule1, rule2, "Rule must be a prototype");
	}
	
	@Test
	void testCorrectRuleRemainedCorrect(){
		CorrectRule rule1 = (CorrectRule) applicationContext.getBean("correctRule");
		CorrectRule rule2 = (CorrectRule) applicationContext.getBean("correctRule");
		
		assertNotSame(rule1, rule2, "Rule must be a prototype");
	}
	
	@Test
	void testRealSingletonRuleRemainSingleton(){
		RealSingletonRule rule1 = (RealSingletonRule) applicationContext.getBean("realSingletonRule");
		RealSingletonRule rule2 = (RealSingletonRule) applicationContext.getBean("realSingletonRule");
		
		assertSame(rule1, rule2, "Rule must be a singleton");
	}
	
	@Test
	void testPoorlyConfiguredSingletonRuleBecameCorrect(){
		PoorlyConfiguredRealSingletonRule rule1 = (PoorlyConfiguredRealSingletonRule) applicationContext.getBean("poorlyConfiguredRealSingletonRule");
		PoorlyConfiguredRealSingletonRule rule2 = (PoorlyConfiguredRealSingletonRule) applicationContext.getBean("poorlyConfiguredRealSingletonRule");
		
		assertSame(rule1, rule2, "Rule must be a singleton");
	}
}
