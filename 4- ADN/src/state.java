/* Vicent Picornell Alandete
 * vicent.de.oliva@gmail.com
 */


import java.util.ArrayList;

public class state {
	String as_string;
	ArrayList<state> allowed;
	state(String state){
		as_string=state;
		allowed=new ArrayList<state>();
	}
	boolean check_compatibility(state st){
		if(st.as_string.length() != this.as_string.length()){
			return false;
		}
		if(st.as_string.equals(this.as_string)){
			return false;
		}
		int differences=0;
		for(int i=0;i<this.as_string.length();i++){
			if(this.as_string.charAt(i) != st.as_string.charAt(i)){
				differences++;
			}
		}
		if(differences>1){return false;}
		else{return true;}
		
	}
	void add_compatibility(state st){
		if(!allowed.contains(st)){
			allowed.add(st);
		}
	}
	boolean equal(state st){
		if(st.as_string.equals(this.as_string)){
			return true;
		}
		return false;
	}
}
