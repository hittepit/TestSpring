package be.fabrice.testspring.postProcessor.bean;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
@CustomAnnotation("toto")
public class SimpleBean {
	private String value;
	
	@PostConstruct
	public void init(){
		value = value.toUpperCase();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
