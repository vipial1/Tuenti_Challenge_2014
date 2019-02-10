
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class PHP{
public static void main ( String[] args ){
	float x=0,y=0;


    FileWriter fichero = null;
    PrintWriter pw = null;
    try
    {
        fichero = new FileWriter(System.getProperty("user.dir")+"/valores.txt");
        pw = new PrintWriter(fichero);

        for (x = 0; x < 30; x+=0.5){
        	for(y=0;y<30;y+=0.5){
        	String result=excutePost("http://gamblers.contest.tuenti.net/index.php", "x="+x+"&y="+y);
	    	int c= result.indexOf("name=\"result\" value=");
	    	result=result.substring(c+21);
	    	for(c=0;c<result.length();c++){
	    		if(result.charAt(c)=='\"'){
	    			result=result.substring(0,c);
	    			break;
	    		}
	    	}
	        pw.print(x+","+y+","+result+";");
        	}
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
       try {
       // Nuevamente aprovechamos el finally para 
       // asegurarnos que se cierra el fichero.
       if (null != fichero)
          fichero.close();
       } catch (Exception e2) {
          e2.printStackTrace();
       }
    }
	//System.out.println(result);
}
public static String excutePost(String targetURL, String urlParameters)
{
  URL url;
  HttpURLConnection connection = null;  
  try {
    //Create connection
    url = new URL(targetURL);
    connection = (HttpURLConnection)url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", 
         "application/x-www-form-urlencoded");
			
    connection.setRequestProperty("Content-Length", "" + 
             Integer.toString(urlParameters.getBytes().length));
    connection.setRequestProperty("Content-Language", "en-US");  
			
    connection.setUseCaches (false);
    connection.setDoInput(true);
    connection.setDoOutput(true);

    //Send request
    DataOutputStream wr = new DataOutputStream (
                connection.getOutputStream ());
    wr.writeBytes (urlParameters);
    wr.flush ();
    wr.close ();

    //Get Response	
    java.io.InputStream is = connection.getInputStream();
    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    String line;
    StringBuffer response = new StringBuffer(); 
    while((line = rd.readLine()) != null) {
      response.append(line);
      response.append('\r');
    }
    rd.close();
    return response.toString();

  } catch (Exception e) {

    e.printStackTrace();
    return null;

  } finally {

    if(connection != null) {
      connection.disconnect(); 
    }
  }
}
}

