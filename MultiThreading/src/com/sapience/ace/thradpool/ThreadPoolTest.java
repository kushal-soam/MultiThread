package com.sapience.ace.thradpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest
{

	public static void main(String[] args)
	{
		BlockingQueue<Runnable>  queue=new ArrayBlockingQueue<>(10);
		ThreadPoolExecutor executor=new ThreadPoolExecutor(1, 1, 10000, TimeUnit.SECONDS, queue);
		new Runnable()
		{
			
			@Override
			public void run()
			{
				
				
			}
		};
		
	}
}
