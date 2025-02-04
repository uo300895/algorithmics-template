package alg;

import java.util.ArrayList;
import java.util.List;

public class JavaA3 {
	
public static boolean primoA2 (int m) {
		
		for (int i = 2; i < m/2+1 ;i++) {
			if (m%i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static List<Integer> listadoDePrimos(int n) {
		List<Integer>primos = new ArrayList<>();
			for (int i = 2; i < n + 1 ; i++) {
				if (primoA2(i)) {
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
