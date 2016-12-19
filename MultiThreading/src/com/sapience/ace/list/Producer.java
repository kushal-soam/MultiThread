package com.sapience.ace.list;

import java.util.List;

public class Producer implements Runnable
{

	private List<Integer> queue;
	private int maxSize = 2;

	public Producer(List<Integer> queue)
	{
		this.queue = queue;
	}

	@Override
	public void run()
	{
		for (int i = 1; i <= 10; i++)
		{ // produce 10 products.
			try
			{
				produce(i);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

	}

	private void produce(int i) throws InterruptedException
	{

		synchronized (queue)
		{
			while (queue.size() == maxSize)
			{
				System.out.println("Queue is full, producerThread is waiting for " + "consumerThread to consume, sharedQueue's size= " + maxSize);
				queue.wait();
			}
		}

		synchronized (queue)
		{
			System.out.println("Produced : " + i);
			queue.add(i);
			Thread.sleep((long) (Math.random() * 1000));
			queue.notify();
		}
	}

}
