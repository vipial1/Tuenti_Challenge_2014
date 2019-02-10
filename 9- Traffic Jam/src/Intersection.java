import java.util.ArrayList;


public class Intersection {
	ArrayList<Road> Roads_out=new ArrayList<Road>();
	ArrayList<Road> Roads_in=new ArrayList<Road>();
	String name;
	int number;
	boolean isCity=false;
	boolean isDeparture=false;
	boolean isActive=true;
	
	int must_income;
	int total_income=-1;
	int balance=-1;
	
	Intersection(String name){
		this.isCity=true;
		if(!name.equals("AwesomeVille")){
			isDeparture=true;
		}
		this.name=name;
	}
	Intersection(int name){
		this.isCity=false;
		this.number=name;
	}
}
