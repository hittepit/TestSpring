package be.fabrice.testspring.aop.annotation;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:/aop/annotation/test-aop-spring.xml")
class TestAop {
	@Autowired
	private SimpleBean simpleBean;
	
	@Test
	void testProxiedAdd(){
		int response = simpleBean.add(6,7);
		assertEquals(response, -1);
		assertEquals(13, simpleBean.originalResponseWas());
	}
	
	@Test
	void testNonProxiedMult(){
		int response = simpleBean.mult(6,7);
		assertEquals(42, response);
		assertEquals(42, simpleBean.originalResponseWas());
	}
}
