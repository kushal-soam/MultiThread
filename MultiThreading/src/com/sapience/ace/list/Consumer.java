package com.sapience.ace.list;

import java.util.List;

public class Consumer implements Runnable
{

	private List<Integer> queue;

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				consume();
			}
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Consumer(List<Integer> tasks)
	{
		super();
		this.queue = tasks;
	}

	private void consume() throws InterruptedException
	{

		synchronized (queue)
		{
			while (queue.size() == 0)
			{
				System.out.println("Queue is empty, consumerThread is waiting for " + "producerThread to produce, queue's size= 0");
				queue.wait();
			}
		}

		synchronized (queue)
		{
			Thread.sleep((long) (Math.random() * 2000));
			System.out.println("CONSUMED : " + queue.remove(0));
			queue.notify();
		}
	}

}
