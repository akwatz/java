import java.util.*;
import java.io.*;
class HappyBus{
	

	public static void main(String[] args){
			BusReservation br = new BusReservation();
			PassengerThread pt1 = new PassengerThread(2,br,"SAM");
			PassengerThread pt2 = new PassengerThread(2, br, "Jack");
			pt1.start();
			pt2.start();
			}
}
class BusReservation implements Runnable{
	private int totalSeatsAvailable=2;
	

	public void run() {
		//BusReservation br = new BusReservation();
		PassengerThread pt = (PassengerThread) Thread.currentThread();
		boolean ticketsBooked = this.bookTickets(pt.getSeatsNeeded(), pt.getName());
		if(ticketsBooked==true){
			System.out.println("CONGRATS"+Thread.currentThread().getName()+" .. The number of seats requested("+pt.getSeatsNeeded()+")  are BOOKED");
		}else{
			System.out.println("Sorry Mr."+Thread.currentThread().getName()+" .. The number of seats requested("+pt.getSeatsNeeded()+")  are not available");	
		}
	}
	
  public  synchronized boolean bookTickets(int seats, String name){
	  System.out.println("Welcome to HappyBus "+Thread.currentThread().getName()+" Number of Tickets Available are:"+this.getTotalSeatsAvailable());
		
		if (seats>this.getTotalSeatsAvailable()) {
			return false;
		} else {
			
			totalSeatsAvailable = totalSeatsAvailable-seats;
			
			return true;
		}
	}

	private int getTotalSeatsAvailable() {
		return totalSeatsAvailable;
	}

}

class PassengerThread extends Thread{
	
		private int seatsNeeded;

		public PassengerThread(int seats, Runnable target, String name) {
			super(target,name);
			this.seatsNeeded = seats;
		}

		public int getSeatsNeeded() {
			return seatsNeeded;
		}
	

}