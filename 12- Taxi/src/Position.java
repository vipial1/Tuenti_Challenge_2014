import java.awt.Point;


public class Position {
	public static final int WALL=0;
	public static final int START=1;
	public static final int DESTINATION=2;
	public static final int EMPTY=-1;
	
	public static final int UP=0;
	public static final int RIGHT=1;
	public static final int DOWN=2;
	public static final int LEFT=3;
	
	Point p;
	Position Turn;
	Position Forward;
	boolean destination_reach=false;
	

	Position(Point p, boolean dest){
		this.p=p;
		this.Forward=null;
		this.Turn=null;
		this.destination_reach=dest;
	}
	
	Position(Point p, int current_iter, int max_iter,int[][] map, int[][][] visited, int direction, boolean turnable){
		this.p=p;
		this.Forward=this.get_Forward(map,visited,direction);
		if(turnable){
			this.Turn=get_Turn(map,visited,direction);
		}
		else{
			this.Turn=null;
		}
		if(this.Forward!=null){
			if(this.Forward.destination_reach){
				this.destination_reach=true;
			}
		}
		if(this.Turn!=null){
			if(this.Turn.destination_reach){
				this.destination_reach=true;
			}
		}
		if(current_iter < max_iter){
			if(this.Forward!=null){
				this.Forward= new Position(this.Forward.p,current_iter+1,max_iter,map,visited,direction, true);
			}
			if(this.Turn!=null){
				this.Turn	= new Position(this.Turn.p,current_iter+1,max_iter,map,visited,(direction+1)%4,true);
			}
		}
		if(this.Forward!=null){
			if(this.Forward.destination_reach){
				this.destination_reach=true;
			}
		}
		if(this.Turn!=null){
			if(this.Turn.destination_reach){
				this.destination_reach=true;
			}
		}
		
	}
	
	Position get_Turn(int[][] map,int[][][] visited,int direction){
		Point turned_p=null;
		int M=map[0].length;
		int N=map.length;
		switch(direction){
		case UP:
			if(p.x<M-1){
				turned_p=new Point(p.x+1,p.y);
			}
			break;
		case RIGHT: 
			if(p.y<N-1){
				turned_p=new Point(p.x,p.y+1);
			}
			break;
		case DOWN:
			if(p.x>0){
				turned_p=new Point(p.x-1,p.y);
			}
			break;
		case LEFT: 
			if(p.y>0){
				turned_p=new Point(p.x,p.y-1);
			}
			break;
	}
		if(turned_p!=null){
			if(!Position.Contains(visited[turned_p.y][turned_p.x], (direction+1)%4)){
				switch(map[turned_p.y][turned_p.x]){
					case WALL: return null;
					case EMPTY:
					case START: 
						Position.Add(visited[turned_p.y][turned_p.x], (direction+1)%4);
						return new Position(new Point(turned_p),false);
					case DESTINATION:
						return new Position(new Point(turned_p),true);
				}
			}
		}
		else{return null;}
		return null;
	}
	
	Position get_Forward(int[][] map,int[][][] visited,int direction){
		Point next_p=null;;
		int M=map[0].length;
		int N=map.length;
		switch(direction){
		case UP:
			if(p.y -1 >=0){
				next_p=new Point(p.x, p.y -1);
			}
			break;
		case RIGHT: 
			if(p.x +1 <= M-1){
				next_p=new Point(p.x+1, p.y);
			}
			break;
		case DOWN:
			if(p.y +1 <= N-1){
				next_p=new Point(p.x, p.y+1);
			}
			break;
		case LEFT: 
			if(p.x -1 >= 0){
				next_p=new Point(p.x-1, p.y);
			}
			break;
		}
		if(next_p!=null){
			if(!Position.Contains(visited[next_p.y][next_p.x], direction)){
				switch(map[next_p.y][next_p.x]){
					case WALL: return null;
					case EMPTY:
					case START: 
						Position.Add(visited[next_p.y][next_p.x], direction);
						return new Position(new Point(next_p),false);
					case DESTINATION:
						return new Position(new Point(next_p),true);
				}
			}
		}
		else{return null;}
		return null;
	}
	
	
	static void Add(int[] visited, int direction){
		for(int i=0;i<visited.length;i++){
			if(visited[i]==-1){
				visited[i]=direction;
			}
		}
	}
	
	static boolean Contains(int[] visited, int direction){
		for(int i=0;i<visited.length;i++){
			if(visited[i]==direction){
				return true;
			}
		}
		return false;
	}
	
}
