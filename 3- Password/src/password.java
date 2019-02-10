/*  Vicent Picornell Alandete
 *  vicent.de.oliva@gmail.com
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/* Basicamente he creado el programa PHP.java que lanza peticiones post hacia el servidor y obtiene un monton de valores x,y,z
 * despues proceso estos valores con el matlab, con el programa importar.m y asi saco f(x,y)=sqrt(x^2 + y^2);
 * He adjuntado tanto php.java como importar.m al fichero comprimido.
 */

public class password {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int cases=Integer.parseInt(input);
		String[] output=new String[cases];
		for(int i=0; i<cases;i++){
			input = br.readLine();
			String[] splited = input.split(" ");
			
			float a=Float.parseFloat(splited[0]);
			float b=Float.parseFloat(splited[1]);
			
			output[i]=String.valueOf(Math.round(Math.sqrt(a*a+b*b) * 100.0)/100.0);
		}
		for(int i=0; i<cases;i++){
			System.out.println(output[i]);
		}
	}

}
