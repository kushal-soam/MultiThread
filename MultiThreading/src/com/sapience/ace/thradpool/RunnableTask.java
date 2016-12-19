package com.sapience.ace.thradpool;

import java.util.concurrent.BlockingQueue;

public class RunnableTask implements Runnable
{

	BlockingQueue<Runnable>  queue; 
	
	
	public RunnableTask(BlockingQueue<Runnable> queue)
	{
		super();
		this.queue = queue;
	}


	@Override
	public void run()
	{
		try
		{
			queue.take();
			System.out.println("Hello");
			
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
