package s7;

public class NullPathTimes {
	public static void main(String arg[]) {
		String[] nI=new String[1]; 
		long timeElapsed = 0;
		for (int n = 10; n < 100000; n+=1) {
			nI[0]=String.valueOf(n);
			long start = System.nanoTime();
				for(int i = 0; i<100; i++) {
					NullPath.main(nI);
				}
			long end = System.nanoTime();
			timeElapsed = (end - start) / 1_000_000;
			System.out.print("n= "+n+", time= "+ timeElapsed/100+"\n");
		}

	}

}
