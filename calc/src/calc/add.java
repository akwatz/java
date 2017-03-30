package calc;
import java.util.Scanner;
public class add {
	public static void main(String args[]){
		Scanner wats = new Scanner(System.in);
		double fnum,snum,answer;
		System.out.println("Enter the first number");
		fnum = wats.nextDouble();
		System.out.println("Enter the second number");
		snum = wats.nextDouble();
		System.out.println(fnum+snum);
	}
}
