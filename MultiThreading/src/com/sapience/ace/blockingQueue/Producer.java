package com.sapience.ace.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable
{

	private BlockingQueue<Task> tasks;

	public Producer(BlockingQueue<Task> tasks)
	{
		this.tasks = tasks;
	}

	@Override
	public void run()
	{

		for(int i=0;i<200;i++)
		{
			try
			{
				tasks.put(new Task(i+""));
			} catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Produce Task "+i);
			try
			{
				Thread.sleep(2);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
