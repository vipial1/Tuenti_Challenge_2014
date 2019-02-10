import java.util.ArrayList;


public class ID {

	int number; //suficiente para 9 digitos
	ArrayList<ID> connections;
	boolean connected_2_A=false;
	boolean connected_2_B=false;
	ID(int n){
		number=n;
		connections=new ArrayList<ID>();
	}
	boolean equals(ID B){
		if(this.number==B.number){
			return true;
		}
		return false;
	}
	public static void add_connection(ID A, ID B){
		if(A.connected_2_A || B.connected_2_A){
			A.connected_2_A=true;
			B.connected_2_A=true;
		}
		if(A.connected_2_B || B.connected_2_B){
			A.connected_2_B=true;
			B.connected_2_B=true;
		}
		if(!A.connections.contains(B)){
			A.connections.add(B);
			if(A.connected_2_A){
				set_as_conected_2_A(A);
			}
			if(A.connected_2_B){
				set_as_conected_2_B(A);
			}
		}
		if(!B.connections.contains(A)){
			B.connections.add(A);
			if(B.connected_2_A){
				set_as_conected_2_A(B);
			}
			if(B.connected_2_B){
				set_as_conected_2_B(B);
			}
		}
		return;
	}
	
	public static void set_as_conected_2_A(ID A){
		ID B,C,D,E;
		if(!A.connected_2_A){ //Aunque esto estéticamente es feo, utiliza 16 veces menos memoria
			A.connected_2_A=true;
			for(int i=0;i<A.connections.size();i++){	
				B=A.connections.get(i);
				if(!B.connected_2_A){
					B.connected_2_A=true;
					for(int j=0;j<B.connections.size();j++){
						C=B.connections.get(j);
						if(!C.connected_2_A){
							C.connected_2_A=true;
							for(int k=0;k<C.connections.size();k++){
								D=C.connections.get(k);
								if(!D.connected_2_A){
									D.connected_2_A=true;
									for(int l=0;l<D.connections.size();l++){
										E=D.connections.get(l);
										if(!E.connected_2_A){
											set_as_conected_2_A(E);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		/*int i=0;
		//Usare DFS ya que usa menos memoria, y en este caso que la entrada es taaan enorme, viene bien.

		for( i=0;i<A.connections.size();i++){
			if(!A.connections.get(i).connected_2_A){
				A.connections.get(i).connected_2_A=true;
				set_as_conected_2_A(A.connections.get(i));
			}
		}*/
	}
	public static void set_as_conected_2_B(ID A){
		
		ID B,C,D,E;
		if(!A.connected_2_B){
			A.connected_2_B=true;
			for(int i=0;i<A.connections.size();i++){	
				B=A.connections.get(i);
				if(!B.connected_2_B){
					B.connected_2_B=true;
					for(int j=0;j<B.connections.size();j++){
						C=B.connections.get(j);
						if(!C.connected_2_B){
							C.connected_2_B=true;
							for(int k=0;k<C.connections.size();k++){
								D=C.connections.get(k);
								if(!D.connected_2_B){
									D.connected_2_B=true;
									for(int l=0;l<D.connections.size();l++){
										E=D.connections.get(l);
										if(!E.connected_2_B){
											set_as_conected_2_B(E);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		/*
		int i=0;
			for(i=0;i<A.connections.size();i++){
				if(!A.connections.get(i).connected_2_B){
					A.connections.get(i).connected_2_B=true;
					set_as_conected_2_B(A.connections.get(i));
				}
			}
			*/
		}
	
}
