package be.fabrice.testspring.injection;

import org.springframework.beans.factory.annotation.Autowired;

public class AnnotatedConstructorClient {
	private Service service;
	
	@Autowired 
	public AnnotatedConstructorClient(Service service) {
		this.service = service;
	}
	
	public Service getService() {
		return service;
	}
}
