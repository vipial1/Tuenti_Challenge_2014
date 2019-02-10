import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class track {
	public final static char DASH= '-';
	public final static char PIPE= '|';
	public final static char SLASH= '/';
	public final static char B_SLASH= '\\';
	public final static char SPACE= ' ';
	public final static char EOL= '\n';
	public final static char FINISH= '#';
	
	public final static char EMPTY='0';
	
	public enum dir {
	    UP, DOWN, RIGHT, LEFT
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int length=input.length();
		char[][] track= new char[2*length][2*length];
		int max_x=length,max_y=length,min_x=length,min_y=length;
		int x=length,y=length;
		dir direction= dir.RIGHT;
		for(int i=0;i<2*length;i++){
			for(int k=0;k<2*length;k++){
				track[i][k]=EMPTY;
			}
		}
		for(int i=0;i<length;i++){
			input.charAt(i);
			switch(input.charAt(i)){
				case DASH:
					switch(direction){
						case UP:
							track[x][y--]=PIPE;
							if(y<min_y){min_y=y;}
							break;
						case DOWN:
							track[x][y++]=PIPE;
							if(y>max_y){max_y=y;}
							break;
						case RIGHT:
							track[x++][y]=DASH;
							if(x>max_x){max_x=x;}
							break;
						case LEFT:
						track[x--][y]=DASH;
						if(x<min_x){min_x=x;}
						break;
					}
					break;
				case FINISH:
					switch(direction){
					case UP:
						track[x][y--]=FINISH;
						if(y<min_y){min_y=y;}
						break;
					case DOWN:
						track[x][y++]=FINISH;
						if(y>max_y){max_y=y;}
						break;
					case RIGHT:
						track[x++][y]=FINISH;
						if(x>max_x){max_x=x;}
						break;
					case LEFT:
					track[x--][y]=FINISH;
					if(x<min_x){min_x=x;}
					break;
				}
					break;
				case SLASH:
					switch(direction){
					case UP:
						direction=dir.RIGHT;
						track[x++][y]=SLASH;
						if(x>max_x){max_x=x;}
						break;
					case DOWN:
						direction=dir.LEFT;
						track[x--][y]=SLASH;
						if(x<min_x){min_x=x;}
						break;
					case RIGHT:
						direction=dir.UP;
						track[x][y--]=SLASH;
						if(y<min_y){min_y=y;}
						break;
					case LEFT:
						direction=dir.DOWN;
						track[x][y++]=SLASH;
						if(y>max_y){max_y=y;}
						break;
					}
					break;
				case B_SLASH:
					switch(direction){
					case DOWN:
						direction=dir.RIGHT;
						track[x++][y]=B_SLASH;
						if(x>max_x){max_x=x;}
						break;
					case UP:
						direction=dir.LEFT;
						track[x--][y]=B_SLASH;
						if(x<min_x){min_x=x;}
						break;
					case LEFT:
						direction=dir.UP;
						track[x][y--]=B_SLASH;
						if(y<min_y){min_y=y;}
						break;
					case RIGHT:
						direction=dir.DOWN;
						track[x][y++]=B_SLASH;
						if(y>max_y){max_y=y;}
						
						
						break;
					}
					break;
			
			}
			
		}
		for(int k=min_y;k<=max_y;k++){
			for(int i=min_x;i<=max_x;i++){
				if(track[i][k]==EMPTY){
					System.out.print(SPACE);
				}	
				else{
					System.out.print(track[i][k]);
				}
			}
			System.out.print(EOL);
		}
	}

}
