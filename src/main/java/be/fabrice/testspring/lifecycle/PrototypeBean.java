package be.fabrice.testspring.lifecycle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {
	private List<String> operations = new ArrayList<String>(); 
	
	private InjectedBean injectedBean;
	
	public PrototypeBean(){
		operations.add("constructor");
	}
	
	@PostConstruct
	public void doAtStart(){
		operations.add("init");
	}
	
	@PreDestroy
	public void destroy(){
		operations.add("destroy");
	}
	
	public void setInjectedBean(InjectedBean injectedBean) {
		operations.add("injection");
		this.injectedBean = injectedBean;
	}

	public List<String> getOperations() {
		return this.operations;
	}

}
