import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;


public class Trains_game {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Station> Stations;
		Hashtable<String,Station> stations = null;
		ArrayList<Route> Routes;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		int[][] scores;
		String[] orige;
		input=br.readLine();
		int N=Integer.parseInt(input);
		scores=new int[N][];
		for(int cases=0;cases<N;cases++){
			Stations = new ArrayList<Station>();
			stations = new Hashtable<String,Station>();
			input=br.readLine();
			String[] splited=input.split(",");
			int S=Integer.parseInt(splited[0]);
			int R=Integer.parseInt(splited[1]);
			int F=Integer.parseInt(splited[2]);
			scores[cases]=new int[R];
			for(int i=0;i<S;i++){
				input=br.readLine();
				splited=input.split(" ");
				String[] splited2 = splited[1].split(",");
				stations.put(splited[0], new Station(splited[0],new Point(Integer.parseInt(splited2[0]),Integer.parseInt(splited2[1])),splited[2],Integer.parseInt(splited[3]),F));
				Stations.add(new Station(splited[0],new Point(Integer.parseInt(splited2[0]),Integer.parseInt(splited2[1])),splited[2],Integer.parseInt(splited[3]),F));
			}
			Routes=new ArrayList<Route>();
			orige=new String[R];
			for(int i=0;i<R;i++){
				Route r=new Route();
				input=br.readLine();
				splited=input.split(" ");
				orige[i]=splited[0];
				for(int j=1;j<splited.length;j++){
					String[] splited2=splited[j].split("-");
					if(r.t_stations.get((splited2[0]))==null){
						r.Stations.add(splited2[0]);
						r.t_stations.put(splited2[0], stations.get(splited2[0]).Clone());
					}
					if(r.t_stations.get(splited2[1])==null){
						r.Stations.add(splited2[1]);
						r.t_stations.put(splited2[1], stations.get(splited2[1]).Clone());
					}
					r.t_stations.get(splited2[0]).conection.add(r.t_stations.get(splited2[1]));
					r.t_stations.get(splited2[1]).conection.add(r.t_stations.get(splited2[0]));
				}
				Routes.add(r);

			}
			for(int i=0;i<R;i++){
				int fuel=F;
				Station or	= Routes.get(i).t_stations.get(orige[i]);
				Station s	= Routes.get(i).t_stations.get(orige[i]);
				String last=null;
				while(fuel>0){
					int j;
					for(j=0;j<s.conection.size();j++){
						float d=distance(s,s.conection.get(j));
						if(d>0 && d<=fuel && (s.conection.size()==1 || !s.conection.get(j).name.equals(last))){
							last=s.name;
							s=s.conection.get(j);
							if(or.wagons.size()!=0){
								if(s.name.equals(or.wagons.get(0).dest)){
									scores[cases][i]+=or.wagons.get(0).Value;
									//last=null;
									or.wagons.remove(or.wagons.get(0));
									or=s;
								}
								else{
									if(s.wagons.size()!=0){
										if(s.wagons.get(0).Value>or.wagons.get(0).Value
											&& Routes.get(i).Stations.contains(s.wagons.get(0).dest)){
											s.wagons.add(or.wagons.get(0));
											or.wagons.remove(or.wagons.get(0));
											or=s;
										}
									}
								}
							}
							else{
								or=s;
							}
							fuel-=d;
							break;
						}
					}
				}
			}


		}
		
		for(int j=0;j<N;j++){
			int value=0;
			for(int i=0;i<scores[j].length;i++){
				value+= scores[j][i];
			}
			System.out.println(value);
		}
		
	}
	
	static float distance(Station A, Station B){
		float r=0;
		r=(B.position.x-A.position.x)*(B.position.x-A.position.x) + (B.position.y-A.position.y)*(B.position.y-A.position.y);
		return (float) Math.sqrt(r);
	}

}
