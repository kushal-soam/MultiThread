package com.sapience.ace;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author vkapo3
 *
 */
public class FixedThreadPool
{

	private volatile boolean shutdown = false;

	private final int poolSize;
	private final List<Runnable> tasks;
	private volatile List<Thread> threadPool;

	private final Object poolLock = new Object();

	private Runnable pool = new Runnable()
	{

		public void run()
		{
			try
			{
				exit: while (!shutdown || (shutdown && hasTask()))
				{

					try
					{
						while (!hasTask())
						{
							if (shutdown)
							{
								break exit;
							}
							Thread.sleep(1000);
						}
					} catch (InterruptedException iex)
					{
						if (!shutdown)
						{
							System.out.println("Pool thread interrupted: " + iex);
						}
					}

					// Execute task
					Runnable task = nextTask();
					if (task != null)
					{
						task.run();
					}
				}
			} catch (Exception ex)
			{
				// Capture exception so pool thread does not die abruptly
				System.err.println("Exception occurred: ");
				ex.printStackTrace();
			}
		};
	};

	private FixedThreadPool(int poolSize)
	{

		tasks = new ArrayList<Runnable>();

		this.poolSize = poolSize;
	}

	public static FixedThreadPool newInstance(int poolSize)
	{

		FixedThreadPool pool = new FixedThreadPool(poolSize);
		pool.createAndStart();

		return pool;
	}

	private void createAndStart()
	{
		threadPool = Stream.generate(() -> new Thread(pool)).limit(poolSize).collect(Collectors.toList());
		threadPool.forEach(Thread::start);
	}

	public void execute(Runnable task)
	{
		synchronized (poolLock)
		{
			tasks.add(task);
		}
	}

	public Runnable nextTask()
	{
		synchronized (poolLock)
		{
			return (!tasks.isEmpty() ? tasks.remove(0) : null);
		}
	}

	public boolean hasTask()
	{
		synchronized (poolLock)
		{
			return !tasks.isEmpty();
		}
	}

	public boolean shutdown()
	{
		if (!shutdown)
		{
			shutdown = true;
		}

		for (Thread t : threadPool)
		{
			while (t.isAlive())
				;
		}

		return true;
	}

	public static void main(String[] args) throws Exception
	{

		BigDecimal one = new BigDecimal("1");
		class Task implements Runnable
		{

			private int number;

			Task(int number)
			{
				this.number = number;
			}

			@Override
			public void run()
			{
				System.out.printf("Thread: %s Factorial of %s is %s \n", Thread.currentThread().getName(), number, factorial(new BigDecimal(number)));
			}

			private BigDecimal factorial(BigDecimal n)
			{
				return (n.compareTo(one) <= 0) ? one : factorial(n.subtract(one)).multiply(n);
			}
		}
		;

		FixedThreadPool pool = FixedThreadPool.newInstance(5);
		Arrays.stream(new int[]
		{ 5, 10, 20, 30, 40, 50, 60, 70, 80, 90 }).forEach(e -> pool.execute(new Task(e)));

		pool.shutdown();

		System.out.println("Program terminated !!");
	}
}