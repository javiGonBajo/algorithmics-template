package algstudent.s3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calendar {
	private String[] participants;
	private String[][] table;
	
	public Calendar(String fileName) {
		participants = loadNamesFromFile(fileName);
		table = new String[participants.length][participants.length];
	}
	
	public Calendar(int n) {
		participants = new String[n];
		table = new String[n][n];
	}
	
	public static void main(String[] arg) {
		Calendar c = new Calendar(arg[0]);
		c.makeTable();
		c.printCalendar();
	}
	
	private String[] loadNamesFromFile(String fileName) {
		String[] names = null; // to store the real names of players
		BufferedReader reader = null; // to read the file

		try {
			reader = new BufferedReader(new FileReader(fileName)); // open the file to be read
			names = new String[Integer.parseInt(reader.readLine())]; // get the number of players (first line)
			int i = 0;
			while (reader.ready())
				names[i++] = reader.readLine(); // keep names of players

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return names;
	}
	
	public String[][] getTable(){
		return table;
	}
	
	private void printCalendar() {
		System.out.print("PLAYER/OPPONENT\t");
		for(int i=1;i<participants.length;i++)
			System.out.print("\t" + "DAY " + i + "\t");
		System.out.println();
		for(int i = 0; i<table.length;i++) {
			for(int j = 0; j<table.length;j++) {
				System.out.print("\t" + table[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public void makeTable() {
		table = makeTable(0, participants.length-1);
	}
	
	//O(n^2)
	// a = 2, b = 2, k = 2
	private String[][] makeTable(int first, int last) {
		int mid = (last-first)/2;
		String[][] aux = new String[last-first+1][last-first+1];
		if(mid == 0) {
			aux[0][0] = participants[first];
			aux[1][0] = participants[last];
			aux[0][1] = participants[last];
			aux[1][1] = participants[first];
		}
		
		else {
			String[][] a = makeTable(first, mid + first);
			for(int i = first;i<=mid + first;i++) {
				for(int j = 0; j<a.length;j++) {
					aux[(i)%aux.length][(j)%aux.length] = a[(i)%a.length][(j)%a.length];
					aux[aux.length - 1 - (i)%aux.length][aux.length -1 - (j)%aux.length] = a[(i)%a.length][(j)%a.length];
				}
			}
			
			a = makeTable(mid+first+1, last);
			for(int i = mid +first+ 1; i<=last;i++) {
				for(int j = 0; j<a.length;j++) {
					aux[(i)%aux.length][(j)%aux.length] = a[(i)%a.length][(j)%a.length];
					aux[aux.length - 1 - (i)%aux.length][aux.length - 1 - (j)%aux.length] = a[(i)%a.length][(j)%a.length];
				}
			}
		}
		return aux;
	}
	
	
	
	
	
}
