import java.util.Hashtable;
import java.util.LinkedList;


public class Route {
	LinkedList<String> Stations;
	Hashtable<String,Station> t_stations;
	
	Route(){
		Stations=new LinkedList<String>();
		t_stations=new Hashtable<String,Station>();
	}
}
