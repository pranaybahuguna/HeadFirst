package com.threads;

public class MyRunnable implements Runnable{

	@Override
	public void run() {
          go();		
	}

	private void go() {
		
		try{
			Thread.sleep(5000);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
         doMore();		
	}

	private void doMore() {
         System.out.println("My Thread One");		
	}

}
