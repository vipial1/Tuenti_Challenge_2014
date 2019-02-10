/* Vicent Picornell Alandete
 * vicent.de.oliva@gmail.com
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ADN {
	public static ArrayList<state> visited=new ArrayList<state>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<state> states=new ArrayList<state>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		state initial = new state(input);
		input = br.readLine();
		state goal = new state(input);
		while(br.ready()){
			input=br.readLine();
			state st= new state(input);
			states.add(st);
		}
		
		//Creamos el arbol logico a partir de los posibles cambios
		for(int k=0; k<states.size();k++){
			if(initial.check_compatibility(states.get(k))){
				initial.add_compatibility(states.get(k));
			}
		}
		for(int i=0;i<states.size();i++){
			for(int k=0; k<states.size();k++){
				if(states.get(i).check_compatibility(states.get(k))){
					states.get(i).add_compatibility(states.get(k));
				}
			}
		}
		
		String output= "";
		//Buscamos el estado final, a traves del algoritmo DFS
		System.out.println(find_goal(output,initial,goal));
	}
	public static String find_goal(String output, state root,state goal){
		if(output.isEmpty()){
			output=root.as_string;
		}
		else{
			output+="->" + root.as_string;	
		}
		for(int i=0;i<visited.size();i++){
			if(visited.get(i).equal(root)){
				return "null";
			}
		}
		visited.add(root);
		if(root.equal(goal)){
			return (output);
		}
		else{
			for(int i=0;i<root.allowed.size();i++){
				String returned=find_goal(output,root.allowed.get(i),goal);
				if(!returned.endsWith("null")){
					return returned;
				}
			}
			return "null";
		}
	}
}
