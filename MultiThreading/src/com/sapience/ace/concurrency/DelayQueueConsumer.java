package com.sapience.ace.concurrency;

import java.util.concurrent.BlockingQueue;

public class DelayQueueConsumer implements Runnable
{

	private BlockingQueue<DelayedElement> elements;

	public DelayQueueConsumer(BlockingQueue<DelayedElement> elements)
	{
		super();
		this.elements = elements;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				DelayedElement delayedElement = elements.take();
				System.out.println("Consumed " + delayedElement.toString());
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
