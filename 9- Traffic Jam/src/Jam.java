import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;


public class Jam {
	public final static String AwesomeCity= "AwesomeVille";
	static Intersection Departure;
	static Intersection Arrival;
	static Hashtable<Integer,Intersection> Intersections=new Hashtable<Integer,Intersection>();
	static Hashtable<Integer,Road> Roads=new Hashtable<Integer,Road>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int cases = Integer.parseInt(input);
		String city;
		int S,D, inter, roads;
		String[] splited;
		String[] output= new String[cases];
		Intersection[] I = new Intersection[2];
		for(int i=0;i<cases;i++){
			city=br.readLine();
			Departure=new Intersection(city);
			Arrival=new Intersection(AwesomeCity);
			input=br.readLine();
			splited = input.split(" ");
			S= Integer.parseInt(splited[0]);
			D= Integer.parseInt(splited[1]);
			input=br.readLine();
			splited = input.split(" ");
			inter= Integer.parseInt(splited[0]);
			Intersections= new Hashtable<Integer,Intersection>();
			Roads=new Hashtable<Integer,Road>();
			for(int j=0;j<inter;j++){
				Intersections.put(j, new Intersection(j));
			}
			roads= Integer.parseInt(splited[1]);
			for(int j=0;j<roads;j++){
				input=br.readLine();
				splited = input.split(" ");
				for(int k=0;k<2;k++){
					if(splited[k].equals(city)){
						I[k]=Departure;
					}
					else if(splited[k].equals(AwesomeCity)){
						I[k]=Arrival;
					}
					else{
						int num=Integer.parseInt(splited[k]);
						I[k]=Intersections.get(num);
					}
				}
				if(I[1].equals(Departure) ||  I[0].equals(Arrival)){continue;}
				Roads.put(j, new Road(I[0],I[1],(splited[2].equals("normal")?S:D),Integer.parseInt(splited[3]),j));
				I[0].Roads_out.add(Roads.get(j));
				I[1].Roads_in.add(Roads.get(j));	
			}
			output[i]=city + " " + Process();
		}
		for(int i=0;i<cases;i++){
			System.out.println(output[i]);
		}
	}

	private static String Process() {

		
		//Interseciones sin flujo de entrada, sobran, les quitamos la conexion, y sin flujo de salida igual
		boolean changes=false;
		do{
			changes=false;
			for(int i=0;i<Intersections.size();i++){
				Intersection I=Intersections.get(i);
				if(I.isActive){
					if(I.Roads_in.size() == 0){
						for(int j=0;j<I.Roads_out.size();j++){
							Road r=I.Roads_out.get(j);
							r.To.Roads_in.remove(r);
							I.Roads_out.remove(r);
						}
						changes=true;
					}
					if(I.Roads_out.size()==0){
						for(int j=0;j<I.Roads_in.size();j++){
							Road r=I.Roads_in.get(j);
							r.From.Roads_out.remove(r);
							I.Roads_in.remove(r);
						}
						changes=true;
					}
					if(I.Roads_in.size()==0 && I.Roads_out.size()==0){
						I.isActive=false;
					}
				}
			}
		}while(changes);
		

		

		//Si un nodo solo tiene una entrada y una salida, se quita y se conectan directamente.
		do{
			changes=false;
			for(int i=0;i<Intersections.size();i++){
				Intersection I=Intersections.get(i);
				if(I.isActive){
					if(I.Roads_in.size()==1 && I.Roads_out.size()==1){
						int j=Roads.size();
						Road r= I.Roads_in.get(0).Max_capacity>I.Roads_out.get(0).Max_capacity?I.Roads_out.get(0):I.Roads_in.get(0);
						Roads.put(j, new Road(I.Roads_in.get(0).From,I.Roads_out.get(0).To,r.Max_Speed,r.lanes,j));
						I.Roads_in.get(0).From.Roads_out.add(Roads.get(j));
						I.Roads_out.get(0).To.Roads_in.add(Roads.get(j));
						I.Roads_in.get(0).From.Roads_out.remove(I.Roads_in.get(0));
						I.Roads_out.get(0).To.Roads_in.remove(I.Roads_out.get(0));
						if(I.balance>0){
							Roads.get(j).current_cap=Math.min(Roads.get(j).Max_capacity, I.balance);
							I.balance -= Roads.get(j).current_cap;
						}
						else{
							Roads.get(j).current_cap=r.current_cap;
						}
						
						if(Roads.get(j).To.equals(Arrival)){
							Roads.get(j).requested=Roads.get(j).Max_capacity;
							if(Roads.get(j).From.equals(Departure)){
								Roads.get(j).current_cap=Roads.get(j).Max_capacity;
							}
							if(Roads.get(j).From.balance > 0){
								Roads.get(j).current_cap = Math.min(Roads.get(j).Max_capacity, Roads.get(j).From.balance);
								Roads.get(j).From.balance -= Roads.get(j).current_cap;
							}
						}
						else{
							Roads.get(j).To.balance=I.balance;
							Roads.get(j).To.must_income=I.must_income;
						}
						I.isActive=false;
						changes=true;
					}
				}
		}
		}while(changes);
		//Intersecciones desde origen, tienen al menos la carretera llena
		for(int i=0;i<Departure.Roads_out.size();i++){
			Road r= Departure.Roads_out.get(i);
			r.current_cap=r.Max_capacity;
			r.To.must_income=r.Max_capacity;
			r.To.balance=r.Max_capacity;
		}

		//Si un nodo solo tiene una salida, el siguiente nodo tiene todo el trafico.
		for(int i=0;i<Intersections.size();i++){
			Intersection I=Intersections.get(i);
			if(I.isActive){
				if(I.Roads_out.size() == 1){
					if(I.Roads_out.get(0).Max_capacity <= I.balance && I.balance>0){
						I.Roads_out.get(0).current_cap=I.Roads_out.get(0).Max_capacity;
						I.balance-=I.Roads_out.get(0).Max_capacity;
						I.Roads_out.get(0).To.balance=I.Roads_out.get(0).current_cap;
					}
					else if (I.balance>0){
						I.Roads_out.get(0).current_cap= I.balance;
						I.balance=0;
						I.Roads_out.get(0).To.balance=I.Roads_out.get(0).current_cap;
					}
				}
			}
		}
		
		//Carreteras que van al destino, quieren estar llenas
		for(int i=0;i<Arrival.Roads_in.size();i++){
			Road r= Arrival.Roads_in.get(i);
			r.requested=r.Max_capacity;
			if(r.From.balance >= r.Max_capacity){
				r.From.balance-=r.Max_capacity;
				r.current_cap=r.Max_capacity;
			}
			else{
				r.current_cap=r.From.balance;
				r.From.balance=0;
			}
			if(r.From==Departure){
				r.current_cap=r.Max_capacity;
			}
			
		}
		
		
		int output=0;
		for(int i=0;i<Arrival.Roads_in.size();i++){
			Road r= Arrival.Roads_in.get(i);
			if(r.current_cap!=-1){
				output+=r.current_cap;
			}
		}
		//if(output==0) return "16200";
		return String.valueOf(output);
		
	}

}
