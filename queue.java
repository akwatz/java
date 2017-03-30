import java.io.*;
import java.util.*;
class Queue <t>{
	private t arr[];
	private int size;
	private int front = -1, rear = -1;
	public Queue(int size){
		this.size = size;
		arr = (t[]) new Object[size];
	}
	public void Enqueue(t num){
		if(front == -1 && rear == -1)
			front = 0;
		if(rear >= size -1){
			System.out.println("Queue is FULL");
			return;
		}
		rear += 1;
		//System.out.println("Number is " + num);
		arr[rear] = num;
	}
	public t Front(){
		if(front > rear)
			return null;
		else
			//System.out.println("Front element is " + arr[front]);
			return arr[front];
	}
	public void Dequeue(){
		if(front >= rear){
			System.out.println("Queue is Empty");
			return;
		}
		front ++;
	}
	public static void main(String args[]){
		Scanner wats = new Scanner(System.in);
		System.out.println("Enter your choice of Queue");
		System.out.println("1. Queue for Characters");
		System.out.println("2. Queue for Integers");
		System.out.println("3. Queue for Floating point numbers");
		int choice;
		choice = wats.nextInt();
		System.out.println("Enter the size of the Queue");
		int size = wats.nextInt();
		Queue <Integer> qi = new Queue(size);
		Queue <Character> qc = new Queue(size);
		Queue <Double> qd = new Queue(size);
		Boolean c = false , d = false , i = false;
		if(choice == 1)
			c = true;
		else if(choice == 2)
			i = true;
		else
			d = true;
		do{
			System.out.println("Enter your choice");
			System.out.println("1.Enqueue");
			System.out.println("2.Dequeue");
			System.out.println("3.Peek the Front element");
			System.out.println("4.Exit");
			choice = wats.nextInt();
			switch (choice) {
				case 1:
					if(c){
						System.out.println("Give your input");
						char temp = wats.next().charAt(0);
						qc.Enqueue(temp);
					}
					else if(i){
						System.out.println("Give your input");
						int temp = wats.nextInt();
						qi.Enqueue(temp);
					}
					else{
						System.out.println("Give your input");
						double temp = wats.nextFloat();
						qd.Enqueue(temp);
					}
					break;
				case 2:
					if(c)
						qc.Dequeue();
					else if(d)
						qd.Dequeue();
					else	
						qi.Dequeue();
						break;
				case 3:
					if(c){
						if(qc.Front() == null)
							System.out.println("Queue is Empty");
						else
							System.out.println(qc.Front());
					}
					else if(d){
						if(qd.Front() == null)
							System.out.println("Queue is Empty");
						else
							System.out.printf("%f\n",qd.Front());
					}
					else{
						if(qi.Front() == null)
							System.out.println("Queue is Empty");
						else
							System.out.println(qi.Front());
					}	
				default:
					break;
			}
		}while(choice != 4);
	}
}
