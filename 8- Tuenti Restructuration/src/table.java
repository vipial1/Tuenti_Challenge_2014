import java.awt.Point;
import java.util.ArrayList;


public class table {
	public final static int same_edge=1;
	public final static int different_edge=3;
	public static int EDGE_SIZE;
	
	short[][] table;
	table(int edge_size){
		this.table = new short[edge_size][edge_size];
		EDGE_SIZE= edge_size;
	}
	table(table t){
		EDGE_SIZE=t.EDGE_SIZE;
		this.table = new short[EDGE_SIZE][EDGE_SIZE];
		for(int i=0;i<EDGE_SIZE;i++){
			for(int j=0;j<EDGE_SIZE;j++){
				this.table[i][j]=t.table[i][j];
			}
		}
	}
	
	public boolean equal(table desired){
		
	/*	if(desired.table[0][0]==3 && desired.table[0][1]==1 && desired.table[0][0]==2 &&
				desired.table[1][0]==0 && desired.table[1][1]==4 && desired.table[1][2]==5 &&
			desired.table[2][0]==6 && desired.table[2][1]==7 && desired.table[2][2]==8 ){
			System.out.println("desired FOUND");
		}
		if(this.table[0][0]==3 && this.table[0][1]==1 && this.table[0][0]==2 &&
				this.table[1][0]==0 && this.table[1][1]==4 && this.table[1][2]==5 &&
				this.table[2][0]==6 && this.table[2][1]==7 && this.table[2][2]==8 ){
				System.out.println("THIS FOUND");
			}
		*/
		for(int i=0;i<EDGE_SIZE;i++){
			for(int j=0;j<EDGE_SIZE;j++){
				if(this.table[i][j]!=(desired.table[i][j])){
					return false;
				}
			}
		}
		return true;
	}
	public void change(Point origin, Point destination){
		short aux;

		aux= this.table[destination.x][destination.y];
		this.table[destination.x][destination.y] = this.table[origin.x][origin.y];
		this.table[origin.x][origin.y]=aux;
	}
	public static boolean possible_swapping(Point origin, Point destination){
		if(origin.equals(destination)){return false;}
		if(origin.x == destination.x && (origin.x==0 || origin.x == EDGE_SIZE-1)){
			return true;
		}
		else if(origin.y == destination.y && (origin.y==0 || origin.y == EDGE_SIZE-1)){
			return true;
		}
		return false;
	}
	public boolean alreadyTested(ArrayList<table> tables){
		for(int i=0;i<tables.size();i++){
			if(this.equal(tables.get(i))){
				return true;
			}
		}
		return false;
	}

	public static boolean Point_correct(Point p, table c, table d){
		if(c.table[p.x][p.y] == d.table[p.x][p.y]){
			return true;
		}
		return false;
	}
	
	public static int change_point(Point origin, Point destination,table c){
		short aux;

		if(origin.equals(destination)){return 0;}
		aux= c.table[destination.x][destination.y];
		c.table[destination.x][destination.y] = c.table[origin.x][origin.y];
		c.table[origin.x][origin.y]=aux;
		
		if(origin.x == destination.x && (origin.x==0 || origin.x == EDGE_SIZE-1)){
			return 1;
		}
		else if(origin.y == destination.y && (origin.y==0 || origin.y == EDGE_SIZE-1)){
			return 1;
		}
		return 3;
	}
	public static Point desired_point(short n, table t){
		for(int i=0;i<EDGE_SIZE;i++){
			for(int j=0;j<EDGE_SIZE;j++){
				if(t.table[i][j]==n){
					return new Point(i,j);
				}
			}
		}
		return new Point(0,0);
	}
	public static int Process(table current, table desired, ArrayList<table> tables){
		/*int result=0;
		do{
			for(int i=0;i<EDGE_SIZE;i++){
				for(int j=0;j<EDGE_SIZE;j++){
					if(!Point_correct(new Point(i,j), current, desired)){
						result+=change_point(new Point(i,j),desired_point(current.table[i][j], desired),current);
					}
				}
			}
		}while(!current.equal(desired));
		return result;*/
		int result = 0;
		int i,j,x,y;
		Point origin, destination;
		for(i=0;i<EDGE_SIZE;i++){
			for(j=0;j<EDGE_SIZE;j++){
				origin=new Point(i,j);
				for(x=0;x<EDGE_SIZE;x++){
					for(y=0;y<EDGE_SIZE;y++){
						destination= new Point(x,y);
						if(possible_swapping(origin,destination)){
							current.change(origin, destination);
							if(current.alreadyTested(tables)){
								current.change(destination, origin);
							}
							else{
								if(current.equal(desired)){
									//System.out.println("Desired found");
									return 1;
								}
								else{
									tables.add(new table(current));
									result=Process(current,desired, tables);
									if(result!=-1){
										return 1+result;
									}
								}
							}
						}
					}
				}	
			}
		}
		//System.out.println("Returning -1 for matrix: "+ current.table[0][0] + current.table[0][1] +current.table[0][2] +current.table[1][0] +current.table[1][1] +current.table[1][2] +current.table[2][0] +current.table[2][1] +current.table[2][2]);
		return -1;
	}
}
