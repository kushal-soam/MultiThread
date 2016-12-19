package com.sapience.ace.concurrency;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class DelayQueueProducer implements Runnable
{

	private BlockingQueue<DelayedElement> blockingQueue;
	final Random random = new Random();

	public DelayQueueProducer(BlockingQueue<DelayedElement> blockingQueue)
	{
		super();
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 100; i++)
		{
			try
			{
				blockingQueue.put(new DelayedElement(random.nextDouble() + "", random.nextInt(50)));
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
