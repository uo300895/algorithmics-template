package algstudent.s3;

import java.io.*;
import java.util.*;

public class Calendar1 {
    public static void main(String[] args) {
        
        List<String> fileList = new ArrayList<>();
        loadFile(args[0], fileList);
        
        int n = Integer.parseInt(fileList.get(0).trim());
        fileList.remove(0);
        
        String[] participants = new String[n];
        for (int i = 0; i < n; i++) {
        	participants[i] = fileList.get(i).trim();
        }
        
        String[][] schedule = generateTable(participants);
        
        System.out.print("PLAYER/\nOPPONENT ");
        for (int day = 1; day <= n - 1; day++) {
            System.out.print("DAY " + day + "\t");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(participants[i] + "\t");
            for (int j = 0; j < n - 1; j++) {
                System.out.print(schedule[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
 
    public static String[][] generateTable(String[] participants) {
        int n = participants.length;
        String[][] table = new String[n][n - 1];
        
        if (n == 2) {
        	table[0][0] = participants[1];
        	table[1][0] = participants[0];
            return table;
            
        } else {
            int m = n / 2;
            String[] left = Arrays.copyOfRange(participants, 0, m);
            String[] right = Arrays.copyOfRange(participants, m, n);
            
            String[][] leftSchedule = generateTable(left);
            String[][] rightSchedule = generateTable(right);
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m - 1; j++) {
                	table[i][j] = leftSchedule[i][j];
                	table[i + m][j] = rightSchedule[i][j];
                }
            }
            
            for (int r = 0; r < m; r++) {
                int round = m - 1 + r; 
                for (int i = 0; i < m; i++) {
                	table[i][round] = right[(i + r) % m];
                	table[i + m][round] = left[(i - r + m) % m];
                }
            }
            return table;
        }
    }
    
    public static void loadFile(String fileName, List<String> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    list.add(line);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + fileName);
        } catch (IOException ioe) {
            throw new RuntimeException("I/O error: " + ioe.getMessage());
        }
    }
}

