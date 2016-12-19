package com.sapience.ace.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadTest
{

	public static void main(String[] args)
	{
		BlockingQueue<Task> tasks=new ArrayBlockingQueue<>(10);
		new Thread(new Producer(tasks)).start();
		new Thread(new Consumer(tasks)).start();
	}
}
