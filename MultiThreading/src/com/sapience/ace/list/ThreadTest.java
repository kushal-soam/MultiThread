package com.sapience.ace.list;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest
{

	public static void main(String[] args) throws InterruptedException
	{
		List<Integer> customList = new ArrayList<Integer>();
		new Thread(new Producer(customList)).start();
		new Thread(new Consumer(customList)).start();
		Thread.sleep(7000);
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
