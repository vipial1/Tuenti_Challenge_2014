import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class survey {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        File file = null;
        FileReader fr = null;
        
        String line = br.readLine();
        int cases=Integer.parseInt(line);
        String[] inputs = new String [cases];
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();       
        for(int i=0;i<cases;i++){
        	inputs[i]=br.readLine();
        	results.add(i,new ArrayList<String>());
        }

         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         file = new File (System.getProperty("user.dir")+"/students");
         fr = new FileReader (file);
         br = new BufferedReader(fr);
         
  
         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null){
            for(int i=0;i<cases;i++){
            	if(linea.contains(inputs[i])){
            		String[] parts = linea.split(",");
            		results.get(i).add(parts[0]);
            		continue;
            	}
            }
         }
         for(int i=0;i<cases;i++){
        	 if(results.get(i).isEmpty()){
        		 System.out.println("Case #" + (i+1) + ": NONE");
        	 }
        	 else{
        		Object[] sorted = results.get(i).toArray();
        		Arrays.sort(sorted);
        		
        		 System.out.print("Case #" + (i+1) + ": ");
        		 for(int x=0;x<sorted.length;x++){
        			 System.out.print(sorted[x]);
        			 if(x!=sorted.length-1){
        				 System.out.print(",");
        			 }
        		 }
        		 System.out.println();
        	 }
         }
	}

}
