package com.threads;

public class RunThreads implements Runnable{
	
	public static void main(String[] a){
		
		RunThreads runner = new RunThreads();
		Thread alpha = new Thread(runner);
		Thread beta = new Thread(runner);
		alpha.setName("Alpha thread");
		beta.setName("Beta thread");
		alpha.start();
		beta.start();
		
	}

	@Override
	public void run() {
		for(int i=0;i<20;i++){
			String name = Thread.currentThread().getName();
			System.out.println(name + "is running");
		}		
	}
}
