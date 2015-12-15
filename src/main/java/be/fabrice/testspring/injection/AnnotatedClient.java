package be.fabrice.testspring.injection;

import org.springframework.beans.factory.annotation.Autowired;

public class AnnotatedClient {
	@Autowired
	private Service service;
	
	public Service getService() {
		return service;
	}
}
