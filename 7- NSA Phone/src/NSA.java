import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Hashtable;


public class NSA {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Hashtable<Integer,ID> IDs = new Hashtable<Integer,ID>();
        File file = null;
        FileReader fr = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		ID A = new ID(Integer.parseInt(input));
		A.connected_2_A=true;
		IDs.put(A.number, A);
		input = br.readLine();
		ID B = new ID(Integer.parseInt(input));
		B.connected_2_B=true;
		IDs.put(B.number, B);
		
        // Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        file = new File (System.getProperty("user.dir")+"/phone_call.log");
        if(file==null){return ;}
        fr = new FileReader (file);
        br = new BufferedReader(fr);
		
        String[] numbers;
        ID C, D;
        int IDasInt1, IDasInt2;
        int log_count=0;
        while((input=br.readLine())!=null){
        	numbers=input.split(" ");
        		IDasInt1=Integer.parseInt(numbers[0]);
        		C=IDs.get(IDasInt1);
        		if(C == null){
        			C= new ID(IDasInt1);
        			IDs.put(IDasInt1, C);
        		}
        		IDasInt2=Integer.parseInt(numbers[1]);
        		D=IDs.get(IDasInt2);
        		if(D == null){
        			D= new ID(IDasInt2);
        			IDs.put(IDasInt2, D);

        		}
    
        	ID.add_connection(C, D);
        	
        	if((C.connected_2_A && D.connected_2_B)){
        		System.out.print("Connected at " + log_count);
        		br.close();
        		return;
        	}
        	
        	log_count++;
         }
        br.close();
        System.out.print("Not connected");
	}
	
	

}
