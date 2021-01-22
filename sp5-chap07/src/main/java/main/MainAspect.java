package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.ReCalculator;
import config.AppCtx;

public class MainAspect {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		ReCalculator cal = ctx.getBean("calculator",ReCalculator.class);
		
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		System.out.println(cal.getClass().getName());
		ctx.close();
	}
}
