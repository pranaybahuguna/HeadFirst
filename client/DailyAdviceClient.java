package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DailyAdviceClient {
	
	public void go(){
		try {
			
			Socket s = new Socket("127.0.0.1",4242);
			InputStreamReader lowLevelReader = new InputStreamReader(s.getInputStream());
			BufferedReader highLevelReader = new BufferedReader(lowLevelReader);
			String advise = highLevelReader.readLine();
			System.out.println("Today's advise : " + advise);
			highLevelReader.close();
			s.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {		
		DailyAdviceClient client = new DailyAdviceClient();
		client.go();
	}

}
