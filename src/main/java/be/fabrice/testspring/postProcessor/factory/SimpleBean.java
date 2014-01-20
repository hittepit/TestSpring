package be.fabrice.testspring.postProcessor.factory;

import org.springframework.stereotype.Component;

@Component
@CustomAnnotation("toto")
public class SimpleBean {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
