package be.fabrice.testspring.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AnnotatedQualifiedClient {
	@Autowired
	@Qualifier("serviceTwo")
	private Service service;
	
	public Service getService() {
		return service;
	}
}
