import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class Password {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		 URL direction = new URL("http://random.contest.tuenti.net/?input=ee8e38ea35");
	        URLConnection yc = direction.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) 
	            System.out.println(inputLine);
	        in.close();
		}catch(Exception e){
			
		}
	}

}
