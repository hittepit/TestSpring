package be.fabrice.testspring.async;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface AsyncBean {
	Future<BigInteger> asyncFact(BigInteger n);
	Future<BigInteger> asyncRecursiveFact(final BigInteger n) throws InterruptedException, ExecutionException ;
	Future<BigInteger> asyncRecursiveFactOther(final BigInteger n);
	Future<Integer> infiniteLoop();
}
