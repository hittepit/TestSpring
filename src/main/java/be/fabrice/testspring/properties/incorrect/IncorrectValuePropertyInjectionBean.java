package be.fabrice.testspring.properties.incorrect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("valueBean")
public class IncorrectValuePropertyInjectionBean {
	@Value("${property.name}")
	private String name;
	@Value("${property.age}")
	private int age;
	@Value("${property.unkonwn}")
	private String nothing;
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getNothing() {
		return nothing;
	}
}
