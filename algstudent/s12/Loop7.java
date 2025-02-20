package algstudent.s12;

public class Loop7 {
	
	public static long loop7(long n) {
		long cont = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++){
				for (int k = 0; k < n; k++) {
					for (int l = 0; l < n; l++) {
						cont++;
					}
				}
			}
		}
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2;
		int nTimes = Integer.parseInt(arg[0]);

		System.out.println("n\ttime\trepetions\tcounter");

		for (long n = 100; n <= 819200; n *= 2) {
			long c = 0;
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++)
				c = loop7(n);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes + "\t\t" + c);
		} // for
	} // main

}
