import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;


public class Restructuration {

	public final static int EDGE_SIZE = 3;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<table> tables = new ArrayList<table>();
		Hashtable<String,Short> Name2number = new Hashtable<String,Short>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int cases = Integer.parseInt(input);
		table current = new table(EDGE_SIZE);
		table desired = new table(EDGE_SIZE);
		//String[][] current_table = new String[EDGE_SIZE][EDGE_SIZE];
		//String[][] desired_table = new String[EDGE_SIZE][EDGE_SIZE];
		String[] splited;
		short counter=0;
		for(int i=0;i<cases;i++){
			input=br.readLine();
			for(int j=0;j<EDGE_SIZE;j++){
				input=br.readLine();
				splited=input.split(", ");
				for(int k=0;k<EDGE_SIZE;k++){
					Name2number.put(splited[k], counter);
					current.table[j][k]=counter++;
				}
			}
			input=br.readLine();
			for(int j=0;j<EDGE_SIZE;j++){
				input=br.readLine();
				splited=input.split(", ");
				for(int k=0;k<EDGE_SIZE;k++){
					desired.table[j][k]=Name2number.get(splited[k]);
				}
			}
			/*if(current.equal(desired)){
				System.out.println("Son iguales");
			}*/
			System.out.println(table.Process(current, desired, tables));
				
		}
	}

}
