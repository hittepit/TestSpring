package be.fabrice.testspring.async;

import java.math.BigInteger;
import java.util.concurrent.Future;

public interface AsyncBean {
	Future<BigInteger> asyncFact(BigInteger n);
}
