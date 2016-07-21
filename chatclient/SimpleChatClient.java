package com.chatclient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class SimpleChatClient {
	
	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;
	
	public static void main(String[] a){
		SimpleChatClient  client = new SimpleChatClient();
		client.go();
	}

	private void go() {

		JFrame frame = new JFrame("Simple chat client");
		JPanel mainPanel = new JPanel();
		incoming = new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		outgoing = new JTextField(200);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(qScroller);
		qScroller.add(outgoing);
		mainPanel.add(sendButton);
		setUpNetworking();
		
		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(400, 500);
		frame.setVisible(true);
		
	}
	
	private void setUpNetworking() {		
		try {
			sock = new Socket("127.0.0.1", 8049);
			InputStreamReader steamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(steamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Network established");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	
	public class SendButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
             
			try{
				
				writer.println(outgoing.getText());
				writer.flush();
				
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
			outgoing.setText("");
			outgoing.requestFocus();
		}
		
	} // inner class
	
	public class IncomingReader implements Runnable{

		@Override
		public void run() {
          String message;
          try{
        	  
        	  while((message = reader.readLine()) != null){
        		  System.out.println("Read " + message );
        		  incoming.append(message);
        	  }
        	  
          } catch(Exception ex){
        	  ex.printStackTrace();
          }
		}
		
	}// inner class
	
	
}// outer class
