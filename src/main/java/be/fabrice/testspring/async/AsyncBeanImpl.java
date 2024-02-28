package be.fabrice.testspring.async;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
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
		while(counter.compareTo(n) < 0){
			accu = accu.multiply(counter);
			counter = counter.add(BigInteger.ONE);
		}
		final CompletableFuture<BigInteger> future = new CompletableFuture<>();
		future.complete(accu);
		return future;
	}
	
	@Async
	public Future<BigInteger> asyncRecursiveFact(final BigInteger n) throws InterruptedException, ExecutionException {
		final CompletableFuture<BigInteger> future = new CompletableFuture<>();
		if(n.equals(BigInteger.ZERO)){
			future.complete(BigInteger.ONE);
		} else {
			future.complete(n.multiply(asyncRecursiveFact(n.subtract(BigInteger.ONE)).get()));
		}
		return future;
	}
	
	@Async
	public Future<BigInteger> asyncRecursiveFactOther(final BigInteger n) {
		final CompletableFuture<BigInteger> future = new CompletableFuture<>();
		future.complete(fact(n));
		return future;
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
		final CompletableFuture<Integer> future = new CompletableFuture<>();
		future.complete(loop());
		return future;
	}
	
	private Integer loop(){
		int i = 1;
		while(i!=0){
			i = -i;
		}
		return i;
	}
}
