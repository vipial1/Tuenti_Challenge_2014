
public class Road {
	Intersection From, To;
	int Max_capacity=-1;
	int Max_Speed=-1;
	int current_cap=-1;
	int requested=-1;
	int lanes=-1;
	int name=-1;


	Road(Intersection From, Intersection To, int Max_Speed, int lanes, int name){
		this.From=From;
		this.To=To;
		this.Max_Speed=Max_Speed;
		this.lanes=lanes;
		this.Max_capacity= lanes * (Max_Speed*1000)/5;
		this.name=name;
	}
}
