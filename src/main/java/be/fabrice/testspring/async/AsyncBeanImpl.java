package be.fabrice.testspring.async;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncBeanImpl implements AsyncBean {
	
	@Async
	public Future<BigInteger> asyncFact(final BigInteger n) {
		return new AsyncResult<BigInteger>(fact(n));
	}
	
	private BigInteger fact(BigInteger n){
		if(n.equals(BigInteger.ZERO)){
			return BigInteger.ONE;
		} else {
			return n.multiply(fact(n.subtract(BigInteger.ONE)));
		}
	}
}
