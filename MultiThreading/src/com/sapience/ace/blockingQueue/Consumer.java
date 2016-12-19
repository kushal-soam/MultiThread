package com.sapience.ace.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable
{

	private BlockingQueue<Task> tasks;

	@Override
	public void run()
	{
		try
		{
			while (!tasks.isEmpty())
			{
				System.out.println("Consume task "+tasks.take().getMsg());
				Thread.sleep(300);
			}
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Consumer(BlockingQueue<Task> tasks)
	{
		super();
		this.tasks = tasks;
	}

}
