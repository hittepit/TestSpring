package be.fabrice.testspring.injection;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class TestInjection {
	@Test(description="injection par constructeur")
	public void testSpringWithConstructor(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-constructor.xml");
		
		ConstructorClient client = (ConstructorClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(FirstServiceImpl.class);
	}
	
	@Test(description="injection par setter")
	public void testSpringWithSetter(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-setter.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(FirstServiceImpl.class);
	}
	
	@Test(description="autowiring byName")
	public void testSpringAutowiringByName(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byName.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(FirstServiceImpl.class);
	}
	
	@Test(description="autowiring byName pas de bean du nom")
	public void testSpringAutowiringByNameWithoutBeanOfName(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byName-no-bean.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isNull();
	}
	
	@Test(description="autowiring byType")
	public void testSpringAutowiringByType(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byType.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(FirstServiceImpl.class);
	}
	
	@Test(description="autowiring byType pas de bean du type")
	public void testSpringAutowiringByTypeWithoutBeanOfType(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byType-no-bean.xml");
		
		SetterClient client = (SetterClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isNull();
	}
	
	@Test(description="autowiring byType deux beans du type", expectedExceptions=UnsatisfiedDependencyException.class)
	public void testSpringAutowiringByTypeTwoBeans(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-byType-two-beans.xml");
	}
	
	@Test(description="annotation autowiring byName pas de bean du nom")
	public void testSpringAnnotatedAutowiringByNameWithoutBeanOfName(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-byName.xml");
		
		AnnotatedClient client = (AnnotatedClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(Service.class);
	}
	
	@Test(description="annotation autowiring byType pas de bean du nom")
	public void testSpringAnnotatedAutowiringByTypeWithoutBeanOfName(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-byType.xml");
		
		AnnotatedClient client = (AnnotatedClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(Service.class);
	}
	
	@Test(description="annotation autowiring sur constructeur")
	public void testSpringAnnotatedAutowiringOnConstructor(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-constructor.xml");
		
		AnnotatedConstructorClient client = (AnnotatedConstructorClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(Service.class);
	}
	
	@Test(description="annotation autowiring pas de bean du type", expectedExceptions=BeanCreationException.class)
	public void testSpringAnnotatedAutowiringByTypeWithoutBeanOfType(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-no-bean.xml");
	}
	
	@Test(description="annotation autowiring deux beans du type", expectedExceptions=BeanCreationException.class)
	public void testSpringAnnotatedAutowiringByTypeWithTwoBeanOfType(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-two-beans.xml");
	}
	
	@Test(description="annotation autowiring deux beans du même types avec qualifier")
	public void testSpringAnnotatedAutowiringByTypeWithTwoQualifiedBeans(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-two-qualified-beans.xml");
		
		AnnotatedQualifiedClient client = (AnnotatedQualifiedClient) ctx.getBean("client");
		
		assertThat(client).isNotNull();
		assertThat(client.getService()).isInstanceOf(Service.class);
		assertThat(client.getService()).isInstanceOf(SecondServiceImpl.class);
	}
	
	@Test(description="annotation autowiring avec qualifier pas de bean avec le même qualifier", expectedExceptions=BeanCreationException.class)
	public void testSpringAnnotatedAutowiringQualifierNoBeanWithSameQualifier(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("injection/spring-autowired-annotated-qualified-bean-different.xml");
	}
}
