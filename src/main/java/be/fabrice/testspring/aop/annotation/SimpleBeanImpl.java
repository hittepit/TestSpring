package be.fabrice.testspring.aop.annotation;

import org.springframework.stereotype.Component;

@Component
public class SimpleBeanImpl implements SimpleBean {
	private int originalResponseWas;
	
	@JustDoIt
	public int add(int a, int b) {
		originalResponseWas = a+b;
		return originalResponseWas;
	}

	public int mult(int a, int b){
		originalResponseWas = a*b;
		return originalResponseWas;
	}
	
	public int originalResponseWas(){
		return this.originalResponseWas;
	}
}
