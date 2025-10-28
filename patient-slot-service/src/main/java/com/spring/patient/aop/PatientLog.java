package com.spring.patient.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class PatientLog {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(PatientLog.class);
	
	
	@Around("execution(* com.spring.patient.controller.*.*(..))")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start=System.currentTimeMillis();
		
		Object proceed = joinPoint.proceed();
		long end=System.currentTimeMillis();
		
	    LOGGER.info("Current Time Taken "+" "+ (end-start)+"ms");
	    
	    return proceed;
	}

}
