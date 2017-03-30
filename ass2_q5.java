import java.util.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


abstract class citizen{
	int wealth, min_wealth, favour, rank, id, age = 0;
	static int population = 0;
	boolean isAlive;
	citizen overlord;	
	void die(){
		isAlive = false;
		System.out.println("Another one bites the dust !!");
		overlord.wealth += wealth;
		wealth = 0;
	}
	void revolt(){
		System.out.println(id+" is revolting !!");
		buyFavour();
		if (favour > overlord.favour)
			overlord.die();
		else
			die();
		overlord.favour -= rank; 
	}
	void addUnderling(citizen new_underling)
	{
	}
	abstract void buyFavour();
	abstract void earn();
	abstract int giveWealth(citizen c, int w);
}

class royal extends citizen{
	citizen [] underling;
	int no_of_underling ;
	royal(citizen overlord){
		id = ++population;
		isAlive = true;
		wealth = 100;
		min_wealth = 100;
		this.overlord = overlord;
		no_of_underling = 0;
	}
	void addUnderling(citizen new_underling){
		if (no_of_underling++ == 0){
			underling = new citizen[1];
		}else{
			underling = Arrays.copyOf(underling, no_of_underling);
		}	
		underling[no_of_underling-1] = new_underling;
	}
	int giveWealth(citizen c, int w){
		if (wealth >= w){	
			favour += c.rank;
			wealth -=w;
			c.wealth += w;
		}else{
			favour -= c.rank;
		}
		return w;
	}
	void buyFavour(){
		overlord.wealth = wealth;
		favour += wealth;
		wealth = 0;
	}
	void earn(){
		for(int i =0; i < no_of_underling;i++){
			wealth += underling[i].giveWealth(this, underling[i].rank);
		}
	}
}

class prince extends royal{
	prince(citizen k){
		super(k);
		rank = 5;
	}	
	void complainToKingAbout(citizen c){
		c.favour -=10/c.rank;
		favour -=1;
	}
	private king inheritsThrone(citizen underling[]){
		king k = new king();
		k.underling = underling;
		die();
		k.wealth = wealth;
		return k;
	}
}

class king extends prince{
	king(){
		super(null);
		rank = 10;
	}
	void buyFavour(){
		favour += wealth;
		wealth = 0;
	}
	public void earn(){
		super.earn();		
		buyFavour();
	}
	void complainToKingAbout(citizen c, king k){
		k.favour +=10;
		super.complainToKingAbout(c);
	}
	void revolt(){
		System.out.println("The KING is revolting !!");
		wealth +=100; 
	}
	void wageWarAgaint(king k){
		if (favour > k.favour)
			k.die();
		else
			die();
	}
	void die(){
		System.out.println("Kings wealth :"+ wealth);
		System.out.println("The king is dead. Long Live the king!!!");
		isAlive = false;
		for(int i=0; i<no_of_underling;i++){
			underling[i].wealth += wealth/no_of_underling;
		}
		wealth = 0;
	}
}

class peasant extends citizen{
	peasant(citizen r){
		id = ++population;
		isAlive = true;
		overlord = r;	
		wealth = 0;
		min_wealth = 0;
		rank = 1;
	}
	int giveWealth(citizen c, int w){
		wealth -= w;
		return w;
	}
	void earn(){
		wealth++;
	}
	void buyFavour(){
		overlord.wealth = wealth;
		favour += wealth/2;
		wealth = 0;
	}
}

class kingdom{
	citizen citizen_of_country[];
	boolean living[];
	int no_of_princes, no_of_citizen, living_citizen_count;
	kingdom(int no_of_citizen, int no_of_princes){
		this.no_of_princes = no_of_princes;
		this.no_of_citizen = no_of_citizen;
		citizen_of_country =  new citizen[no_of_citizen];
		living=new boolean[no_of_citizen];
		citizen_of_country[0] = new king();
		living[0] = true;
		int i=1;
		for(;i<no_of_princes+1;i++){
			citizen_of_country[i] = new prince(citizen_of_country[0]);
			citizen_of_country[0].addUnderling(citizen_of_country[i]);
			living[i] = true;
		}
		Random r=new Random();
		for(;i<no_of_citizen;i++){
			int overlord_id = r.nextInt(no_of_princes-1) + 1;
			citizen_of_country[i] = new peasant(citizen_of_country[overlord_id]);
			citizen_of_country[overlord_id].addUnderling(citizen_of_country[i]);
			living[i] = true;
		}
	}
	String rank(int rank){
		if (rank==10){
			return "king";
		}else if(rank==5){
			return "prince";
		}else{
			return "peasant";
		} 
	}
	void status(citizen c){
		System.out.println(c.id+"\t a "+rank(c.rank)+" \t("+c.age+")yrs \t"+c.wealth+"$, \t"+c.favour+" favour "+c.isAlive);	
	}
}

class citizenThread implements Runnable{
	citizen c;
	citizenThread(citizen c){
		this.c = c;
	}
	public void run(){
		Random r = new Random();
		while(c.isAlive){
			try{
				Thread.sleep(r.nextInt(5000)+5000);
				c.earn();
				if (c.wealth < c.min_wealth){
					c.revolt();
				}
				c.age++;
				if(c.age >= 10){
					c.die();
				}
			}catch(Exception e){
				System.err.println(c.id+ ": "+e.getMessage());
			}
		}
	}	
}

class kingdomThread implements Runnable {
	kingdom k ;
	kingdomThread(kingdom k){
		this.k = k;
	}
	public void run(){
		while(k.living_citizen_count >0){
			for(int i=0;i<k.no_of_citizen;i++){
				citizen c = k.citizen_of_country[i];
				if(k.living[i] && !c.isAlive){
					k.living_citizen_count--;
					k.living[i] = false;
				}
				k.status(c);		
			}
			System.out.println("==================================================================================");
			try
			{
			Thread.sleep(10000);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}

class fantasticKingdom{
	public static void main(String args[]) throws InterruptedException{
		kingdom fantasticKingdom = new kingdom(21,5);			
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new kingdomThread(fantasticKingdom));
		for(int i=0;i<fantasticKingdom.no_of_citizen;i++){
			exec.execute(new citizenThread(fantasticKingdom.citizen_of_country[i]));
		}
		//exec.shutdown();
		//exec.awaitTermination(1, TimeUnit.SECONDS);
	}
}
