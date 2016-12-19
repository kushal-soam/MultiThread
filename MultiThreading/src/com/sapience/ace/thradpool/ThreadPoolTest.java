package com.sapience.ace.thradpool;

public class ThreadPoolTest {

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Runnable task executed");

			}
		};
		ThreadPool threadPool=new ThreadPool(3, 13);
		try {
			for(int i=0;i<15;i++)
			{
				threadPool.execute(new Thread(runnable));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
