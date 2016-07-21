package com.threads;

import com.threads.MyRunnable;

public class ThreadTester {
	
	public static void main(String[] a){
	
		Runnable threadJob = new MyRunnable();
		Thread myThread = new Thread(threadJob);
		
		myThread.start();
		
		System.out.println("Back to the MAIN");				
	}
}
