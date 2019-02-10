import java.awt.Point;
import java.util.ArrayList;


public class Station {
	String name;
	Point position;
	ArrayList<Wagon> wagons;
	float fuel;
	ArrayList<Station> conection;
	
	Station(String name, Point p, String dest, int value, int fuel){
		this.name=name;
		this.position=p;
		this.fuel=fuel;
		conection=new ArrayList<Station>();
		wagons=new ArrayList<Wagon>();
		wagons.add(new Wagon(dest, value));
		
	}
	Station(String name, Point p,ArrayList<Wagon> wagons,float fuel){
		this.name=name;
		this.position=p;
		this.wagons=wagons;
		this.fuel=fuel;
		this.conection=new ArrayList<Station>();
	}
	Station Clone(){
		return new Station(this.name,this.position,this.wagons,this.fuel);
	}
}
