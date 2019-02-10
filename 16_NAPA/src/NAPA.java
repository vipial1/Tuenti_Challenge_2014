import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class NAPA {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = null;
        FileReader fr = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		input=br.readLine();
		String[] splited=input.split(",");
		int N=Integer.parseInt(splited[0]);
		int numbers=Integer.parseInt(splited[1]);
		ArrayList<point> points=new ArrayList<point>();
		file = new File (System.getProperty("user.dir")+"/points");
        fr = new FileReader (file);
        br = new BufferedReader(fr);
        for(int i=1;i<N;i++){
        	br.readLine();
        }
        for(int i=0;i<numbers;i++){
        	input=br.readLine();
        	splited=input.split("\t");
        	while(splited[0].startsWith(" ")){
        		splited[0]=splited[0].substring(1);
        	}
        	while(splited[1].startsWith(" ")){
        		splited[1]=splited[1].substring(1);
        	}
        	while(splited[2].startsWith(" ")){
        		splited[2]=splited[2].substring(1);
        	}
        	points.add(new point(Integer.parseInt(splited[0]),Integer.parseInt(splited[1]),Integer.parseInt(splited[2])));
        }
        br.close();
        int colisions=0;
        for(int i=0;i<points.size()-1;i++){
        	for(int j=i+1;j<points.size();j++){
        		colisions+= check_colision(points.get(i), points.get(j));
        	}
        }
        System.out.println(colisions);
		
	}
	static int check_colision(point a, point b){
		
		long x=(b.p.x-a.p.x);
		x=x*x;
		long y=(b.p.y-a.p.y);
		y=y*y;
		long sum=x+y;
		double distance= Math.sqrt(sum);
		
		if(distance < (double)a.radius+(double)b.radius){return 1;}
		
		return 0;
	}

}
