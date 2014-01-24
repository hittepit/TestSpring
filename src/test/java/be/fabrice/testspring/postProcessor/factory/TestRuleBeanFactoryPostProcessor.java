package be.fabrice.testspring.postProcessor.factory;

import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations="classpath:postProcessor/factory/test-factoryPostProcessor-spring.xml")
public class TestRuleBeanFactoryPostProcessor extends AbstractTestNGSpringContextTests{
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void testBadlyConfiguredRuleBecameCorrect(){
		BadlyConfiguredRule rule1 = (BadlyConfiguredRule) applicationContext.getBean("badlyConfiguredRule");
		BadlyConfiguredRule rule2 = (BadlyConfiguredRule) applicationContext.getBean("badlyConfiguredRule");
		
		assertNotSame(rule1, rule2, "Rule must be a prototype");
	}
	
	@Test
	public void testCorrectRuleRemainedCorrect(){
		CorrectRule rule1 = (CorrectRule) applicationContext.getBean("correctRule");
		CorrectRule rule2 = (CorrectRule) applicationContext.getBean("correctRule");
		
		assertNotSame(rule1, rule2, "Rule must be a prototype");
	}
	
	@Test
	public void testRealSingletonRuleRemainSingleton(){
		RealSingletonRule rule1 = (RealSingletonRule) applicationContext.getBean("realSingletonRule");
		RealSingletonRule rule2 = (RealSingletonRule) applicationContext.getBean("realSingletonRule");
		
		assertSame(rule1, rule2, "Rule must be a singleton");
	}
	
	@Test
	public void testPoorlyConfiguredSingletonRuleBecameCorrect(){
		PoorlyConfiguredRealSingletonRule rule1 = (PoorlyConfiguredRealSingletonRule) applicationContext.getBean("poorlyConfiguredRealSingletonRule");
		PoorlyConfiguredRealSingletonRule rule2 = (PoorlyConfiguredRealSingletonRule) applicationContext.getBean("poorlyConfiguredRealSingletonRule");
		
		assertSame(rule1, rule2, "Rule must be a singleton");
	}
}
