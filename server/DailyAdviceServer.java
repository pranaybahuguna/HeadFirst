package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {
	
	String[] adviceList = {"Take smaller bites","One word : inappropriate","Just today, be honest","Might have to rethink that haircut",
			"You might get lucky"};
	
	public void go(){
		try {
			
			ServerSocket serverSock = new ServerSocket(4242);
			
			while(true){
				
				
				//Write to client
				Socket sock = serverSock.accept();				
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				String advice = getAdvice();
				writer.println(advice);
				writer.close();
				System.out.println(advice);
				serverSock.close();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private String getAdvice() {
		int random = (int) (Math.random()*adviceList.length);		
		return adviceList[random];
	}

	public static void main(String[] args) {
		
		DailyAdviceServer server = new DailyAdviceServer();
		server.go();
		
	}

}
