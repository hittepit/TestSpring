package be.fabrice.testspring.injection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


class TestInjection {
	@Test
	void testSpringWithConstructor(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-constructor.xml");
		
		ConstructorClient client = (ConstructorClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(FirstServiceImpl.class);
	}
	
	@Test
	void testSpringWithSetter(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-setter.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(FirstServiceImpl.class);
	}
	
	@Test
	void testSpringAutowiringByName(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byName.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(FirstServiceImpl.class);
	}
	
	@Test
	void testSpringAutowiringByNameWithoutBeanOfName(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byName-no-bean.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isNull();
	}
	
	@Test
	void testSpringAutowiringByType(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byType.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(FirstServiceImpl.class);
	}
	
	@Test
	void testSpringAutowiringByTypeWithoutBeanOfType(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byType-no-bean.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isNull();
	}
	
	@Test
	void testSpringAutowiringByTypeTwoBeans(){
		assertThrows(UnsatisfiedDependencyException.class,
				() -> new ClassPathXmlApplicationContext("injection/spring-autowired-byType-two-beans.xml"));
	}
	
	@Test
	void testSpringAnnotatedAutowiringByNameWithoutBeanOfName(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-byName.xml");
		
		AnnotatedClient client = (AnnotatedClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(Service.class);
	}
	
	@Test
	void testSpringAnnotatedAutowiringByTypeWithoutBeanOfName(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-byType.xml");
		
		AnnotatedClient client = (AnnotatedClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(Service.class);
	}
	
	@Test
	void testSpringAnnotatedAutowiringOnConstructor(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-constructor.xml");
		
		AnnotatedConstructorClient client = (AnnotatedConstructorClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(Service.class);
	}
	
	@Test
	void testSpringAnnotatedAutowiringByTypeWithoutBeanOfType(){
		assertThrows(BeanCreationException.class,
				() -> new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-no-bean.xml"));
	}
	
	@Test
	void testSpringAnnotatedAutowiringByTypeWithTwoBeanOfType(){
		assertThrows(BeanCreationException.class,
				() -> new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-two-beans.xml"));
	}
	
	@Test
	void testSpringAnnotatedAutowiringByTypeWithTwoQualifiedBeans(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-two-qualified-beans.xml");
		
		AnnotatedQualifiedClient client = (AnnotatedQualifiedClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(Service.class);
		assertThat(client.getService()).isInstanceOf(SecondServiceImpl.class);
	}
	
	@Test
	void testSpringAnnotatedAutowiringQualifierNoBeanWithSameQualifier(){
		assertThrows(BeanCreationException.class,
				() -> new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-qualified-bean-different.xml"));
	}
}
