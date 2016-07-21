package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection {
	
	public static void main(String[] a){
		
		try {
			
			//Write Data to socket
			Socket writerSocket = new Socket("127.0.0.1", 8028);
			PrintWriter writer = new  PrintWriter(writerSocket.getOutputStream());
			System.out.println("Outgoing message");
			writer.println("message1");
			System.out.println(writer.toString());
			writer.close();
			writerSocket.close();
			
			//Read Data from socket
			Socket readerSocket = new Socket("127.0.0.1", 8028);
			InputStreamReader stream = new InputStreamReader(readerSocket.getInputStream());
			BufferedReader reader = new BufferedReader(stream);
			String msg = reader.readLine();
			System.out.println("Incoming Message : "+msg);
			reader.close();
			readerSocket.close();						
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
