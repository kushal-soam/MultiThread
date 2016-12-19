package com.sapience.ace.concurrency;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedElement implements Delayed
{
	private String element;
	private long expiryTime;

	public DelayedElement(String element, long delay)
	{
		System.out.println("Element: "+element+" and Delay: "+delay);
		this.element = element;
		this.expiryTime =  delay;
	}

	@Override
	public long getDelay(TimeUnit timeUnit)
	{
		long diff = expiryTime - System.currentTimeMillis();
		return timeUnit.convert(diff, TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o)
	{
		if (this.expiryTime < ((DelayedElement) o).expiryTime)
		{
			return -1;
		}
		if (this.expiryTime > ((DelayedElement) o).expiryTime)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public String toString()
	{
		return element + ": " + expiryTime;
	}
}