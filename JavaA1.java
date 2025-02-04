import java.util.ArrayList;
import java.util.List;

public class JavaA1 {
	
	
	public static boolean primoA1 (int m) {
		boolean primo = true;
		
		for (int i = 2; i < m ;i++) {
			if (m%i == 0) {
				primo = false;
			}
		}
		
		return primo;
	}
	
	public static List<Integer> listadoDePrimos(int n) {
		List<Integer>primos = new ArrayList<>();
			for (int i = 0; i < n ; i++) {
				if (primoA1(i)) {
					primos.add(i);
				}
			}
		return primos;
	}
	
	
	public static void main(String[] args) {
		int n= 10000;
		
		for (int i = 0; i < 7; i++) {
			long startTime = System.currentTimeMillis();
			List<Integer> primes = listadoDePrimos(n);
			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;
			System.out.println("n ="+ n + "***" + "time =" + elapsedTime + " miliseconds");
		
			n = n*2;
		}
	}
	    


}