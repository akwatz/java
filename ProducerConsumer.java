import java.util.*;
import java.io.*;
class ProducerConsumer{
	public static void main(String [] args){
		Cup cup = new Cup();
		Producer p1 = new Producer(cup,1);
		Consumer c1 = new Consumer(cup,1);
		p1.start();
		c1.start();
	}
}
class Producer extends Thread{
	private Cup cup;
	private int number;
	public Producer(Cup cup , int number){
		this.cup = cup;
		this.number = number;
	}
	public void run(){
		for(int i = 0 ; i < 10 ; i++){
			cup.put(i);
			System.out.println("Number put to cup is " + i);
		}
	}
}
class Consumer extends Thread{
	private Cup cup;
	private int number;
	public Consumer(Cup c , int n){
		this.cup = c;
		this.number = n;
	}
	public void run(){
		for(int i = 0 ; i < 10 ; i++){
			int num = cup.take();
			System.out.println("Number taken out from cup is " + num);
		}
	}
}
class Cup{
	private int contents;
	boolean available = false;
	public synchronized  int take(){
		while(!available){
			try{
				wait();
			}
			catch(InterruptedException e){}
		}
		available = false;
		notifyAll();
		return contents;
	}
	public synchronized  void put(int i){
		while(available){
			try{
				wait();
			}
			catch(InterruptedException e){}
		}
		contents = i;
		available = true;
		notifyAll();
	}
}