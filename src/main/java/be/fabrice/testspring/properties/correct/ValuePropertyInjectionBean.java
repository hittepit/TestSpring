package be.fabrice.testspring.properties.correct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("valueBean")
public class ValuePropertyInjectionBean {
	@Value("${property.name}")
	private String name;
	@Value("${property.age}")
	private int age;
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
}
