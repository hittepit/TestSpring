package be.fabrice.testspring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyOwnAroundAspect {
	@Around("@annotation(be.fabrice.testspring.aop.annotation.JustDoIt)")
	public Object signal(ProceedingJoinPoint pjp) throws Throwable{
		pjp.proceed();
		
		return -1;
	}
}
