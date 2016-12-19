package com.sapience.ace.customlist;

public class Consumer implements Runnable
{

	private CustomList queue;

	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				while (!queue.getTasks().isEmpty())
				{
					System.out.println("Consume task " + queue.get());
					Thread.sleep(300);
				}
			}
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Consumer(CustomList tasks)
	{
		super();
		this.queue = tasks;
	}

}
