import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Pheasant {
	static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
							  'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String encrypted_folder = System.getProperty("user.dir")+"/feeds/encrypted/";
		byte [] encrypted;
		File file;
		String input;
		String[] splited, splited2, splited3;
		Hashtable<Long,String> TimeStamp2Feed;
		ArrayList<Long> TimeStamps;
		ArrayList<String> output=new ArrayList<String>();

		do{
			int user;
			TimeStamp2Feed=new Hashtable<Long,String>();
			TimeStamps=new ArrayList<Long>();
			String key;
			input=br.readLine();
			splited=input.split("; ");
			for(int i=1;i<splited.length;i++){
				input=splited[i];
				splited2=input.split(",");
				user=Integer.parseInt(splited2[0]);
				key=splited2[1];
				String two_last=String.valueOf((user-((user/100)*100)));
				if(two_last.length()==1){
					two_last="0"+two_last;
				}
				file=new File(encrypted_folder+two_last+"/"+user+".feed");
				FileInputStream fileInput = new FileInputStream(file);
				BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
				encrypted = new byte[(int) file.length()];
				bufferedInput.read(encrypted);
				bufferedInput.close();

				String out=decrpytBytes(encrypted,key, String.valueOf(user));
				out=out.replace('\n', ' ');
				splited3=out.split(" ");
				Long ts=(long) 0;
				for(int x=0;x<splited3.length;x++){
					switch (x%3){
					case 1:
						ts=Long.parseLong(splited3[x]);
						TimeStamps.add(ts);
						break;
					case 2:
						TimeStamp2Feed.put(ts, splited3[x]);
						break;
					}
				}
			}
			Collections.sort(TimeStamps);
			String out="";
			for(int y=0;y<Integer.parseInt(splited[0]) && y<TimeStamps.size();y++){
				out+=" " + TimeStamp2Feed.get(TimeStamps.get(TimeStamps.size()-y-1));
			}
			output.add(out.substring(1));
		}while(br.ready());
		for(int i=0;i<output.size();i++){
			System.out.println(output.get(i));
		}
	}
	public static String decrpytBytes(byte[] encryptedData, String key, String user)
	{
		String output="";
		int i1=0,i2=0,i3=0;

		do{
		    byte[] keyBytes = (key+alphabet[i3]+alphabet[i2]+alphabet[i1]).getBytes();
		    Cipher cipher = null;
	
		    try
		    {
		        cipher = Cipher.getInstance("AES/ECB/NoPadding");
		        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		        cipher.init(Cipher.DECRYPT_MODE, secretKey);
		        output=new String(cipher.doFinal(encryptedData),"ASCII");
		    }
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
		    i1++;
		    if(i1 == alphabet.length){
		    	i1=0;
		    	i2++;
		    	if(i2==alphabet.length){
		    		i2=0;
		    		i3++;
		    		if(i3==alphabet.length){
		    			return null;
		    		}
		    	}
		    }
		}while(!output.startsWith((user)));
		return output;
	}
	public static byte[] convertToByteArray(String key)
	{
	    byte[] b = new byte[key.length()/2];

	    for(int i=0, bStepper=0; i<key.length()+2; i+=2)
	        if(i !=0)
	            b[bStepper++]=((byte) Integer.parseInt((key.charAt(i-2)+""+key.charAt(i-1)), 16));

	    return b;
	}
}
