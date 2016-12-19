package com.sapience.ace.customlist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomList
{

	AtomicInteger counter = new AtomicInteger(0);
	int maxSize;
	List<Task> queue;

	public List<Task> getTasks()
	{
		return queue;
	}

	public void setTasks(List<Task> tasks)
	{
		this.queue = tasks;
	}

	public CustomList(int maxSixe)
	{
		super();
		this.maxSize = maxSixe;
		this.queue = new ArrayList<>();
	}

	public synchronized void put(String msg) throws InterruptedException
	{ // check space is available or not.
		if (queue.size() == maxSize)
		{
			this.wait();
		}

		// space is available, insert element and notify all waiting threads.
		queue.add(new Task(msg));
		//System.out.println("Task Added "+msg);
		notifyAll();
	}

	public synchronized String get() throws InterruptedException
	{// waits element is available or not.
		if (queue.size() == 0)
		{
			queue.wait();
		}

		// element is available, remove element and notify all waiting threads.
		notifyAll();
		return queue.remove(0).getMsg();
	}
}
