package com.sapience.ace;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vkapo3
 *
 */
public class Alternator
{

	private Object lock = new Object();
	private int index = 0;
	private List<Thread> tList = new ArrayList<Thread>();

	private int value = 1;
	private int MAX_LIMIT = 10;

	Alternator(int maxLimit)
	{
		MAX_LIMIT = maxLimit;
	}

	private Runnable task = new Runnable()
	{
		@Override
		public void run()
		{

			while (value <= MAX_LIMIT)
			{

				synchronized (lock)
				{

					try
					{

						while (value <= MAX_LIMIT && !Thread.currentThread().equals(getThread(index)))
						{
							lock.wait();
						}

						if (value > MAX_LIMIT)
							break;

					} catch (Exception e)
					{
						e.printStackTrace();
					}

					int v = getNextValue();
					System.out.println(Thread.currentThread().getName() + " <-> " + v);

					index = index + 1;
					lock.notifyAll();
				}
			}
		}
	};

	private int getNextValue()
	{
		return value++;
	}

	private Thread getThread(int index)
	{
		return tList.get(index % tList.size());
	}

	private void start()
	{
		for (Thread t : tList)
		{
			t.start();
		}
	}

	private void addThread(int threadCount, Runnable task)
	{

		for (int i = 1; i <= threadCount; i++)
		{
			Thread t = new Thread(task);
			tList.add(t);
		}
	}

	private void await() throws InterruptedException
	{
		for (Thread t : tList)
		{
			t.join();
		}
	}

	public static void main(String[] args) throws Exception
	{

		String s=new String("Kushal").intern();
		String s1="Kushal";
		System.out.println(s1==s);
		Alternator alternator = new Alternator(100);

		alternator.addThread(8, alternator.task);
		alternator.start();
		alternator.await();

		System.out.println("\nProgram terminated !!");
	}
}