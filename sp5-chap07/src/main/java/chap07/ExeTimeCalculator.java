package chap07;

public class ExeTimeCalculator implements Calculator{
	//핵심 기능을 구현하지 않고 다른 객체에 위임하는 클래스!
	private Calculator delegate;
	
	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}

	@Override
	public long factorial(long num) {
		// TODO Auto-generated method stub
		long start= System.nanoTime();
		long result = delegate.factorial(num);
		long end = System.nanoTime();
		
		System.out.printf("%s.facotrial(%d) 실행시간 = %d \n", delegate.getClass().getSimpleName(),num,(end-start));
		
		return result;
	}

}
