import java.util.*;
import java.io.*;
import java.lang.*;

//Definition of the generic Queue
class Queue<Q>{	
	class Node{
		Q o;
		Node next;
		Node(Q obj){
			o = obj;
			next = null;
		}
	}
	Node front, back;
	void enqueue(Q obj){
		Node n = new Node(obj); 
		if (front == null){
			front = n;
			back = n;		
		}else{
			back.next = n;
			back = n;
		}
	}
	Q dequeue(){
		if (front == null){
			System.out.println("The Queue is empty!!");
			return null;
		}
		Node obj = front;
		System.out.println(front.o);
		front =  front.next;
		return obj.o;
	}	
	void printContent(){
		Node n = front;
		System.out.print(" The contents of the queue are :\t");
		while(n!= null){
			System.out.print(n.o+"\t");
			n = n.next;
		}
		System.out.println();
	}
}

//Definition of Thread T1
//It is the producer thread
//Will generate random numbers at
//random intervals
class T1 extends Thread{
	private Queue<Integer> q = new Queue<Integer>();
	
	//Creating a boolean variable which will tell the thread when to stop
	private boolean stop;

	//Constructor for the producer thread
	T1(Queue<Integer> queue){
		this.q = queue;
		this.stop = false;
	}

	void Stop(){
		this.stop = true;
	}

	@Override

	public void run(){
		int count = 1;
		Thread s = Thread.currentThread();
		while(!stop){
			count += 1;
			q.enqueue((int)(Math.random() * 1000 + 1));
			try{
				s.sleep((int)(Math.random() * 100 + 1));
			}
			catch(InterruptedException e){}
			if(count == 105)
				Stop();
		}
	}
}

//Definition of Thread T2
class T2 extends Thread{
	private Queue<Integer> q = new Queue<Integer>();
	T2(Queue<Integer> queue){
		this.q = queue;
		Thread s = new Thread(this);
		s.start();
	}

	@Override
	public void run(){
		Thread s = Thread.currentThread();
		try{
			s.sleep(1000);
		}
		catch(InterruptedException e){}
		Integer o = q.dequeue();
		int count = 1;
		while(true){
			if(o == null)
				break;
			o = q.dequeue();
			count += 1;
			if(count == 10){
				count = 0;
				System.out.println();
				try{
					s.sleep(1000);
				}
				catch(InterruptedException e){}
			}
		}
	}
}

//Class containing Main Function
class genericQueue{

	static <T> void test(T[] arr){
		Queue<T> q = new Queue<T>();
		for(int i=0; i<arr.length; i++){
			q.enqueue(arr[i]);
		}
		q. printContent();
		T o = q.dequeue();
		while (o!= null){
			//System.out.println(o);
			o = q.dequeue();
		}
	}

	public static void main(String [] args){
		Queue<Integer> Q= new Queue<Integer>();
		T1 producer = new T1(Q);
		T2 consumer = new T2(Q);
		producer.start();
		//consumer.start();
	}
}