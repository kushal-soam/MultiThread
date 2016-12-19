package com.sapience.ace.customlist;

public class ThreadTest
{

	public static void main(String[] args) throws InterruptedException
	{
		CustomList customList = new CustomList(4);
		new Thread(new Producer(customList)).start();
		new Thread(new Consumer(customList)).start();
		Thread.sleep(7000);
		customList.put("Hi");
		customList.put("Hi 1");
		customList.put("Hi 2");
		customList.put("Hi 3");
		customList.put("Hi 4");
		/*for (int i = 0; i < 100; i++)
		{
			customList.put(i + "");
		}
		for (int i = 0; i < 200; i++)
		{
			System.out.println(customList.get());
		}
		CustomList b = new CustomList(10);
		System.out.println("put(11)");
		b.put("11");
		System.out.println("put(12)");
		b.put("12");
		System.out.println("take() > " + b.get());
		System.out.println("take() > " + b.get());*/
	}
}
