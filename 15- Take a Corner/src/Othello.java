import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Othello {
	public static final char[] x={'a','b','c','d','e','f','g','h'};
	public static final char[] y={'1','2','3','4','5','6','7','8'};
	public static final int EMPTY=-1;
	public static final int BLACK=0;
	public static final int WHITE=1;
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		String[] output;
		int[][] map; //0 for empty, 1 for black, 2 for white
		ArrayList<Point> Empty_points;
		input=br.readLine();
		int N= Integer.parseInt(input);
		output=new String[N];
		for(int n=0;n<N;n++){
			map= new int [8][8];
			Empty_points = new ArrayList<Point>();
			boolean white_moves=true;
			input=br.readLine();
			String[] splited;
			splited=input.split(" ");
			int sol_movs=Integer.parseInt(splited[2]);
			boolean sol_pl_white = splited[0].equals("White")?true:false;
			white_moves=sol_pl_white;
			for(int y=0;y<8;y++){
				input=br.readLine();
				for(int x=0;x<8;x++){
					if(input.charAt(x)=='.'){
						map[y][x]=EMPTY;
						Empty_points.add(new Point(x,y));
						continue;
					}
					if(input.charAt(x)=='X'){
						map[y][x]=BLACK;
						continue;
					}
					if(input.charAt(x)=='O'){
						map[y][x]=WHITE;
						continue;
					}
				}
			}
			int empties= Empty_points.size();
			int map_bu[][]=new int[8][8];
			
			int alloweds0=0;
			int allowed0_times=0;
			do{
                for(int i=0;i<empties;i++){	//Win at 0
                    Point p=Empty_points.get(i);
                    if(allowed_movement(map, p,white_moves?WHITE:BLACK)){ //Win at 0
                        alloweds0++;
                        copy(map_bu,map);
                        move(map,p,white_moves?WHITE:BLACK);
                        Empty_points.remove(p);
                        white_moves = !white_moves;
                        int alloweds1=0;
                        int alloweds1_times=0;
                        do{
                            for(int i1=0;i1<empties-1;i1++){	//Win at 1
                                Point p1=Empty_points.get(i1);
                                int[][] map_bu1=new int[8][8];
                                if(allowed_movement(map, p1,white_moves?WHITE:BLACK)){ //Win at 1
                                    alloweds1++;
                                    copy(map_bu1,map);
                                    move(map,p1,white_moves?WHITE:BLACK);
                                    Empty_points.remove(p1);
                                    white_moves = !white_moves;
                                    
                                    if(sol_movs==2){	//Win at 2
                                        if(check_finish(map,white_moves?WHITE:BLACK)){
                                            output[n]=""+x[p.x]+y[p.y];
                                        }
                                        else{
                                            output[n]=null;
                                            copy(map,map_bu1);
                                            Empty_points.add(i1,p1);
                                            white_moves = !sol_pl_white;
                                            break;
                                        }
                                    }
                                    else{	//Win at 3
                                    	int alloweds2=0;
                                        int alloweds2_times=0;
                                        do{
                                        for(int i2=0;i2<empties-2;i2++){	//Win at 3
                                            Point p2=Empty_points.get(i2);
                                            int[][] map_bu2=new int[8][8];
                                            if(allowed_movement(map, p2,white_moves?WHITE:BLACK)){ //Win at 3
                                                alloweds2++;
                                                copy(map_bu2,map);
                                                move(map,p2,white_moves?WHITE:BLACK);
                                                Empty_points.remove(p2);
                                                white_moves = !white_moves;
                                                
                                                if(sol_movs==3){	//Win at 3
                                                    if(check_finish(map,white_moves?WHITE:BLACK)){
                                                        output[n]=""+x[p.x]+y[p.y];
                                                    }
                                                    else{
                                                        output[n]=null;
                                                        copy(map,map_bu2);
                                                        Empty_points.add(i2,p2);
                                                        white_moves = sol_pl_white;
                                                        break;
                                                    }
                                                }
                                                else{	//Win at 4
                                                    for(int i3=0;i3<empties-3;i3++){	//Win at 4
                                                        Point p3=Empty_points.get(i3);
                                                        int[][] map_bu3=new int[8][8];
                                                        if(allowed_movement(map, p3,white_moves?WHITE:BLACK)){ //Win at 4
                                                            copy(map_bu3,map);
                                                            move(map,p3,white_moves?WHITE:BLACK);
                                                            Empty_points.remove(p3);
                                                            white_moves = !white_moves;
                                                            
                                                            if(sol_movs==4){	//Win at 4
                                                                if(check_finish(map,white_moves?WHITE:BLACK)){
                                                                    output[n]=""+x[p.x]+y[p.y];
                                                                }
                                                                else{
                                                                    output[n]=null;
                                                                    copy(map,map_bu3);
                                                                    Empty_points.add(i3,p3);
                                                                    white_moves = !sol_pl_white;
                                                                    break;
                                                                }
                                                            }
                                                            else{	//Win at 5
                                                                for(int i4=0;i4<empties-4;i4++){	//Win at 5
                                                                    Point p4=Empty_points.get(i4);
                                                                    int[][] map_bu4=new int[8][8];
                                                                    if(allowed_movement(map, p4,white_moves?WHITE:BLACK)){ //Win at 5
                                                                        copy(map_bu4,map);
                                                                        move(map,p4,white_moves?WHITE:BLACK);
                                                                        Empty_points.remove(p4);
                                                                        white_moves = !white_moves;
                                                                        
                                                                        if(sol_movs==5){	//Win at 5
                                                                            if(check_finish(map,white_moves?WHITE:BLACK)){
                                                                                output[n]=""+x[p.x]+y[p.y];
                                                                            }
                                                                            else{
                                                                                output[n]=null;
                                                                                copy(map,map_bu4);
                                                                                Empty_points.add(i4,p4);
                                                                                white_moves = sol_pl_white;
                                                                                break;
                                                                            }
                                                                        }
                                                                        else{	//Win at 6
                                                                            for(int i5=0;i5<empties-5;i5++){	//Win at 6
                                                                                Point p5=Empty_points.get(i5);
                                                                                int[][] map_bu5=new int[8][8];
                                                                                if(allowed_movement(map, p5,white_moves?WHITE:BLACK)){ //Win at 6
                                                                                    copy(map_bu5,map);
                                                                                    move(map,p5,white_moves?WHITE:BLACK);
                                                                                    Empty_points.remove(p5);
                                                                                    white_moves = !white_moves;
                                                                                    
                                                                                    if(sol_movs==6){	//Win at 6
                                                                                        if(check_finish(map,white_moves?WHITE:BLACK)){
                                                                                            output[n]=""+x[p.x]+y[p.y];
                                                                                        }
                                                                                        else{
                                                                                            output[n]=null;
                                                                                            copy(map,map_bu5);
                                                                                            Empty_points.add(i5,p5);
                                                                                            white_moves = !sol_pl_white;
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    
                                                                                    copy(map,map_bu5);
                                                                                    Empty_points.add(i5,p5);
                                                                                    white_moves = !sol_pl_white;
                                                                                }//end if allowed 6
                                                                            }//end for 6
                                                                            
                                                                        }
                                                                        copy(map,map_bu4);
                                                                        Empty_points.add(i4,p4);
                                                                        white_moves = sol_pl_white;
                                                                    }//end if allowed 5
                                                                }//end for 5
                                                                
                                                            }
                                                            copy(map,map_bu3);
                                                            Empty_points.add(i3,p3);
                                                            white_moves = !sol_pl_white;
                                                        }//end if allowed 4
                                                    }//end for 4
                                                    
                                                }
                                                copy(map,map_bu2);
                                                Empty_points.add(i2,p2);
                                                white_moves = sol_pl_white;
                                            }//end if allowed 3
                                        }//end for 3
                                        if(alloweds2==0){alloweds2_times++; white_moves = !white_moves;}
                                        else{alloweds2_times++;}
                                        }while(alloweds2_times==0);
                                    }
                                    copy(map,map_bu1);
                                    Empty_points.add(i1,p1);
                                    white_moves = !white_moves;
                                }//end if allowed 1
                            }//end for
                            if(alloweds1==0){alloweds1_times++; white_moves = !white_moves;}
                            else{alloweds1_times++;}
                        }while(alloweds1_times==0);
                        if(output[n]!=null){break;}
                        copy(map,map_bu);
                        Empty_points.add(i,p);
                        white_moves = !white_moves;
                    }//end if allowed 0
                }//end for 0
                
                if(alloweds0==0){allowed0_times++; white_moves = !white_moves;}
                else{allowed0_times++;}
			}while(allowed0_times==0);
            
		}

		for(int n=0;n<N;n++){
			System.out.println(output[n]==null?"Impossible":output[n]);
		}
	}

	static void print_map(int[][] map){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				switch(map[i][j]){
                    case -1:System.out.print(".");break;
                    case 0:System.out.print("X");break;
                    case 1:System.out.print("O");break;
				}
			}
			System.out.print("\n");
		}
	}
	static void copy(int[][] map_bu, int[][] map){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				map_bu[i][j]=map[i][j];
			}
		}
	}
	
	static void move(int[][]map,Point p, int colour){
		int x,y;
		if(map[p.y][p.x] != EMPTY){return;}
		if(p.x+1 < 7){ //Horizontal a derechas
			if(map[p.y][p.x+1] == (colour+1)%2){
				for(x=p.x+2;x<8;x++){
					if(map[p.y][x] == (colour+1)%2){
						continue;
					}
					if(map[p.y][x] == EMPTY){
						break;
					}
					if(map[p.y][x] == colour){
						map[p.y][p.x]=colour;
						for(int i=p.x+1;i<x;i++){
							map[p.y][i]=colour;
						}
						break;
					}
				}
			}
		}
		if(p.x-1 >0){ //Horizontal a izquierdas
			if(map[p.y][p.x-1] == (colour+1)%2){
				for(x=p.x-2;x>=0;x--){
					if(map[p.y][x] == (colour+1)%2){
						continue;
					}
					if(map[p.y][x] == EMPTY){
						break;
					}
					if(map[p.y][x] == colour){
						map[p.y][p.x]=colour;
						for(int i=p.x-1;i>x;i--){
							map[p.y][i]=colour;
						}
						break;
					}
				}
			}
		}
		if(p.y-1 >0){ //vertical arriba
			if(map[p.y-1][p.x] == (colour+1)%2){
				for(y=p.y-2;y>=0;y--){
					if(map[y][p.x] == (colour+1)%2){
						continue;
					}
					if(map[y][p.x] == EMPTY){
						break;
					}
					if(map[y][p.x] == colour){
						map[p.y][p.x]=colour;
						for(int i=p.y-1;i>y;i--){
							map[i][p.x]=colour;
						}
						break;
					}
				}
			}
		}
		if(p.y+1 < 7){ //Vertical abajo
			if(map[p.y+1][p.x] == (colour+1)%2){
				for(y=p.y+2;y<8;y++){
					if(map[y][p.x] == (colour+1)%2){
						continue;
					}
					if(map[y][p.x] == EMPTY){
						break;
					}
					if(map[y][p.x] == colour){
						map[p.y][p.x]=colour;
						for(int i=p.y+1;i<y;i++){
							map[i][p.x]=colour;
						}
						break;
					}
				}
			}
		}
		
		if(p.y-1 > 0 && p.x+1 <7){ //Diagonal arriba derecha
			if(map[p.y-1][p.x+1] == (colour+1)%2){
				for(y=p.y-2, x=p.x+2; y>=0 && x<8;y--, x++){
					if(map[y][x] == (colour+1)%2){
						continue;
					}
					if(map[y][x] == EMPTY){
						break;
					}
					if(map[y][x] == colour){
						map[p.y][p.x]=colour;
						for(int i=p.y-1, j=p.x+1;i>y && j<x;i--,j++){
							map[i][j]=colour;
						}
						break;
					}
				}
			}
		}
		if(p.y-1 > 0 && p.x-1 >0){ //Diagonal arriba izquierda
			if(map[p.y-1][p.x-1] == (colour+1)%2){
				for(y=p.y-2, x=p.x-2; y>=0 && x>=0;y--, x--){
					if(map[y][x] == (colour+1)%2){
						continue;
					}
					if(map[y][x] == EMPTY){
						break;
					}
					if(map[y][x] == colour){
						map[p.y][p.x]=colour;
						for(int i=p.y-1, j=p.x-1;i>y && j>x;i--,j--){
							map[i][j]=colour;
						}
						break;
					}
				}
			}
		}
		
		if(p.y+1 < 7  && p.x-1 >0){ //Diagonal abajo izquierda
			if(map[p.y+1][p.x-1] == (colour+1)%2){
				for(y=p.y+2, x=p.x-2; y<8 && x>=0;y++, x--){
					if(map[y][x] == (colour+1)%2){
						continue;
					}
					if(map[y][x] == EMPTY){
						break;
					}
					if(map[y][x] == colour){
						map[p.y][p.x]=colour;
						for(int i=p.y+1, j=p.x-1;i<y && j>x;i++,j--){
							map[i][j]=colour;
						}
						break;
					}
				}
			}
		}
		if(p.y+1 < 7  && p.x+1 <7){ //Diagonal abajo derecha
			if(map[p.y+1][p.x+1] == (colour+1)%2){
				for(y=p.y+2, x=p.x+2; y<8 && x<8;y++, x++){
					if(map[y][x] == (colour+1)%2){
						continue;
					}
					if(map[y][x] == EMPTY){
						break;
					}
					if(map[y][x] == colour){
						map[p.y][p.x]=colour;
						for(int i=p.y+1, j=p.x+1;i<y && j<x;i++,j++){
							map[i][j]=colour;
						}
						break;
					}
				}
			}
		}
	}
	
	static boolean check_finish(int[][]map, int colour){
		if(allowed_movement(map,new Point(0,0),colour)){
			return true;
		}
		else if(allowed_movement(map,new Point(7,0),colour)){
			return true;
		}
		else if(allowed_movement(map,new Point(0,7),colour)){
			return true;
		}
		else if(allowed_movement(map,new Point(7,7),colour)){
			return true;
		}
		return false;
	}
    
	
	
	static boolean allowed_movement(int[][]map, Point p, int colour){
		int x,y;
		if(map[p.y][p.x]!=EMPTY){return false;}
		if(p.x+1 < 7){ //Horizontal a derechas
			if(map[p.y][p.x+1] == (colour+1)%2){
				for(x=p.x+2;x<8;x++){
					if(map[p.y][x] == (colour+1)%2){
						continue;
					}
					if(map[p.y][x] == EMPTY){
						break;
					}
					if(map[p.y][x] == colour){
						return true;
					}
				}
			}
		}
		if(p.x-1 >0){ //Horizontal a izquierdas
			if(map[p.y][p.x-1] == (colour+1)%2){
				for(x=p.x-2;x>=0;x--){
					if(map[p.y][x] == (colour+1)%2){
						continue;
					}
					if(map[p.y][x] == EMPTY){
						break;
					}
					if(map[p.y][x] == colour){
						return true;
					}
				}
			}
		}
		if(p.y-1 >0){ //vertical arriba
			if(map[p.y-1][p.x] == (colour+1)%2){
				for(y=p.y-2;y>=0;y--){
					if(map[y][p.x] == (colour+1)%2){
						continue;
					}
					if(map[y][p.x] == EMPTY){
						break;
					}
					if(map[y][p.x] == colour){
						return true;
					}
				}
			}
		}
		if(p.y+1 < 7){ //Vertical abajo
			if(map[p.y+1][p.x] == (colour+1)%2){
				for(y=p.y+2;y<8;y++){
					if(map[y][p.x] == (colour+1)%2){
						continue;
					}
					if(map[y][p.x] == EMPTY){
						break;
					}
					if(map[y][p.x] == colour){
						return true;
					}
				}
			}
		}
		
		if(p.y-1 > 0 && p.x+1 <7){ //Diagonal arriba derecha
			if(map[p.y-1][p.x+1] == (colour+1)%2){
				for(y=p.y-2, x=p.x+2; y>=0 && x<8;y--, x++){
					if(map[y][x] == (colour+1)%2){
						continue;
					}
					if(map[y][x] == EMPTY){
						break;
					}
					if(map[y][x] == colour){
						return true;
					}
				}
			}
		}
		if(p.y-1 > 0 && p.x-1 >0){ //Diagonal arriba izquierda
			if(map[p.y-1][p.x-1] == (colour+1)%2){
				for(y=p.y-2, x=p.x-2; y>=0 && x>=0;y--, x--){
					if(map[y][x] == (colour+1)%2){
						continue;
					}
					if(map[y][x] == EMPTY){
						break;
					}
					if(map[y][x] == colour){
						return true;
					}
				}
			}
		}
		
		if(p.y+1 < 7  && p.x-1 >0){ //Diagonal abajo izquierda
			if(map[p.y+1][p.x-1] == (colour+1)%2){
				for(y=p.y+2, x=p.x-2; y<8 && x>=0;y++, x--){
					if(map[y][x] == (colour+1)%2){
						continue;
					}
					if(map[y][x] == EMPTY){
						break;
					}
					if(map[y][x] == colour){
						return true;
					}
				}
			}
		}
		if(p.y+1 < 7  && p.x+1 <7){ //Diagonal abajo derecha
			if(map[p.y+1][p.x+1] == (colour+1)%2){
				for(y=p.y+2, x=p.x+2; y<8 && x<8;y++, x++){
					if(map[y][x] == (colour+1)%2){
						continue;
					}
					if(map[y][x] == EMPTY){
						break;
					}
					if(map[y][x] == colour){
						return true;
					}
				}
			}
		}
		
		return false;
		
	}
    
}
