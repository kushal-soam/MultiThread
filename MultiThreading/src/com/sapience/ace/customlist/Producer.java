package com.sapience.ace.customlist;

public class Producer implements Runnable
{

	private CustomList queue;

	public Producer(CustomList list)
	{
		this.queue = list;
	}

	@Override
	public void run()
	{

		for (int i = 0; i < 6; i++)
		{
			try
			{
				queue.put(i + "");
			} catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Produce Task " + i);
			try
			{
				Thread.sleep(300);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
