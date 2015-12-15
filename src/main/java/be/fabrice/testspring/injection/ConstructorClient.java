package be.fabrice.testspring.injection;


public class ConstructorClient {
	private Service service;
	
	public ConstructorClient(Service service) {
		this.service = service;
	}
	
	public Service getService() {
		return service;
	}
}
