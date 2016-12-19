package com.sapience.ace.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class DelayedQueueTest
{
	public static void main(String[] args)
	{
		BlockingQueue<DelayedElement> delayedElements=new DelayQueue<>();
		new Thread(new DelayQueueProducer(delayedElements)).start();
		new Thread(new DelayQueueConsumer(delayedElements)).start(); 
	}

}
