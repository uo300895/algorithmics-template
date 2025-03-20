package algstudent.s3;

public class CalendarTimes {
    public static void main(String[] args) {
        int nTimes = Integer.parseInt(args[0]);
        long t1, t2;
        
      
        for (int n = 2; n <= 100000; n *= 2) {
            String[] players = new String[n];
            for (int i = 0; i < n; i++) {
                players[i] = "Jugador" + (i + 1);
            }
            
            t1 = System.currentTimeMillis();
            for (int rep = 0; rep < nTimes; rep++) {
                String[][] schedule = Calendar1.generateTable(players);
                if (schedule == null) {
                    System.out.println("Error al generar el calendario");
                    return;
                }
            }
            t2 = System.currentTimeMillis();
            
            System.out.println("SIZE = " + n + " ** TIME = " + (t2 - t1) + " ms ** nTimes = " + nTimes);
        }
    }
}



