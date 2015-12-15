package be.fabrice.testspring.injection;


public class SetterClient {
	private Service service;
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public Service getService() {
		return service;
	}
}
