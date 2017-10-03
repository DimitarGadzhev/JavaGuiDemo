package com.VCW_2.servTh;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 
import java.util.concurrent.Future; 
import java.util.logging.Level; 
import java.util.logging.Logger;

import junit.framework.Test;



public class FutureDemo {
	
	private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);
	
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		FactorialCalculator task1 = new FactorialCalculator(12);
		Jumper task2 = new Jumper(12); 
		System.out.println("Submitting Task ...");
		Future future1 = threadpool.submit(task1);
		Future future2 = threadpool.submit(task2);
		System.out.println("Task is submitted");
//		while (!future1.isDone()) {
//			System.out.println("Task 1 is not completed yet....");
//			Thread.sleep(1); //sleep for 1 millisecond before checking again
//		}
//		while (!future2.isDone()) {
//			System.out.println("Task 2 is not completed yet....");
//			Thread.sleep(1); //sleep for 1 millisecond before checking again
//		}
		while (!future1.isDone() || !future2.isDone()) {
			if(!future1.isDone()) {
				System.out.println("Task 1 is not completed yet....");
			}
			if (!future2.isDone()) {
				System.out.println("Task 2 is not completed yet....");
			}
			Thread.sleep(1);
		
		}
//		future2.cancel(false);
		System.out.println("Task is completed, let's check result");
		Long factorial = (Long) future1.get();
		String jumps = (String) future2.get();
		System.out.println("Factorial of 12 is : " + factorial);
		System.out.println("Jumps of 12 is : " + jumps);
		threadpool.shutdown();
	}
	
	private static class FactorialCalculator implements Callable<Long> {
		
		private final int number;
		
		public FactorialCalculator(int number) {
			this.number = number;
		}
		
		@Override 
		public Long call() {
			Long output = 0l;
			try {
				output = factorial(number);
			} catch (InterruptedException ex) {
				Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
			}
			return output;
		}
		
		private Long factorial(int number) throws InterruptedException {
			if (number < 0) {
				throw new IllegalArgumentException("Number must be greater than zero");
			}
			long result = 1;
			while (number > 0) {
				Thread.sleep(1); // adding delay for example
				result = result * number;
				number--;
			}
			return result;
		}
	}
	
	private static class Jumper implements Callable<String> {
		private int number = 0;
		
		public Jumper(int number) {
			this.number = number;
		}
		
		@Override
		public String call() {
//			String result = "end";
			return Jump(number);
		}
		
		private String Jump(int number) {
			if (number < 1) {
				return "0";
			}
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<number; i+=2) {
				sb.append("_" + Integer.toString(i));
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		}
	}
	
}
