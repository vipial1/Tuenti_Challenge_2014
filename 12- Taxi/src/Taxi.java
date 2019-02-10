import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Taxi {

	public static final char cWALL='#';
	public static final char cSTART='S';
	public static final char cDESTINATION='X';
	public static final char cEMPTY='.';
	public static final int WALL=0;
	public static final int START=1;
	public static final int DESTINATION=2;
	public static final int EMPTY=-1;

	public static final int UP=0;
	public static final int RIGHT=1;
	public static final int DOWN=2;
	public static final int LEFT=3;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		String[] splited;
		int[][] map;
		int [][][] visited = null;
		Position p;
		input=br.readLine();
		Point Start=new Point(0,0);
		int cases= Integer.parseInt(input);
		int[] dir_sol={2147483647,2147483647,2147483647,2147483647};
		String[] output=new String[cases];
		for(int i=0;i<cases;i++){
			input=br.readLine();
			splited=input.split(" ");
			int M=Integer.parseInt(splited[0]);
			int N=Integer.parseInt(splited[1]);
			map=new int[N][M];
			visited=new int[N][M][4];
			for(int j=0;j<N;j++){
				for(int k=0;k<M;k++){
					for(int l=0;l<4;l++){
						visited[j][k][l]=-1;
					}
				}
			}
			Start=new Point(0,0);
			for(int j=0;j<N;j++){
				input=br.readLine();
				for(int k=0;k<M;k++){
					char c=input.charAt(k);
					if(c==cWALL){
						map[j][k]=WALL;
					}
					else if(c==cSTART){
						map[j][k]=START;
						Start=new Point(k,j);
					}
					else if(c==cDESTINATION){
						map[j][k]=DESTINATION;
					}
					else if(c==cEMPTY){
						map[j][k]=EMPTY;
					}
				}
			}
			
			dir_sol[0]=dir_sol[1]=dir_sol[2]=dir_sol[3]=2147483647;
			for(int n=0;n<4;n++){
				for(int m=0;m<N*M && m<Math.min(dir_sol[0],Math.min(dir_sol[1],Math.min(dir_sol[2], dir_sol[3])));m++){
					visited[Start.y][Start.x][0]=(RIGHT+n)%4;
					p=new Position(Start, 0, m, map, visited, (RIGHT+n)%4, false);
					for(int j=0;j<N;j++){
						for(int k=0;k<M;k++){
							for(int l=0;l<4;l++){
								visited[j][k][l]=-1;
							}
						}
					}
					if(p.destination_reach){
						dir_sol[n]=m+2;
						break;
					}
				}
			}
			int o=(Math.min(dir_sol[0],Math.min(dir_sol[1],Math.min(dir_sol[2], dir_sol[3])))-1);
			
			if(o==2147483646){
				output[i]= "Case #"+(i+1)+": "+"ERROR";
			}
			else{
				output[i]= "Case #"+(i+1)+": " +o;
			}
		}
		for(int i=0;i<cases;i++){
			System.out.println(output[i]);
		}
	}

}
