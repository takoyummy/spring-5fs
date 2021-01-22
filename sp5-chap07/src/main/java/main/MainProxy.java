package main;

import chap07.ExeTimeCalculator;
import chap07.ImpeCalculator;
import chap07.ReCalculator;

public class MainProxy {
	
	public static void main(String[] args) {
		//여기서 ExeTimeCalculator가 프록시( 핵심 기능을 위임하는 객체)
		//ImpeCalculator가 핵심기능을 실행하므로 대상객체! ㅌㅈ
		ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println(ttCal1.factorial(20));
		
		ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new ReCalculator());
		System.out.println(ttCal2.factorial(20));
	}

}
