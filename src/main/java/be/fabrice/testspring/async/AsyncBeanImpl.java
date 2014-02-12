package be.fabrice.testspring.async;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncBeanImpl implements AsyncBean {
	
	@Async
	public Future<BigInteger> asyncFact(final BigInteger n) {
		BigInteger accu = BigInteger.ONE;
		BigInteger counter = BigInteger.ONE;
		while(counter.compareTo(n) != 1){
			accu = accu.multiply(counter);
			counter = counter.add(BigInteger.ONE);
		}
		Future<BigInteger> f = new AsyncResult<BigInteger>(accu);
		System.out.println("Envoyé par bean: "+f);
		return f;
	}
	
	@Async
	public Future<BigInteger> asyncRecursiveFact(final BigInteger n) throws InterruptedException, ExecutionException {
		if(n.equals(BigInteger.ZERO)){
			return new AsyncResult<BigInteger>(BigInteger.ONE);
		} else {
			return new AsyncResult<BigInteger>(n.multiply(asyncRecursiveFact(n.subtract(BigInteger.ONE)).get()));
		}
	}
	
	@Async
	public Future<BigInteger> asyncRecursiveFactOther(final BigInteger n) {
		return new AsyncResult<BigInteger>(fact(n));
	}
	
	private BigInteger fact(BigInteger n){
		if(n.equals(BigInteger.ZERO)){
			return BigInteger.ONE;
		} else {
			return n.multiply(fact(n.subtract(BigInteger.ONE)));
		}
	}
	
	@Async
	public Future<Integer> infiniteLoop(){
		return new AsyncResult<Integer>(loop());
	}
	
	private Integer loop(){
		int i = 1;
		while(i!=0){
			i = -i;
		}
		return i;
	}
}
