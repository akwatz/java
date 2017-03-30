package calc;
import java.util.Scanner;
public class remainder {
	public static void main(String args[]){
		int num1,num2,remainder;
		Scanner wats = new Scanner(System.in);
		System.out.println("Enter the first number");
		num1 = wats.nextInt();
		System.out.println("Enter the second number");
		num2 = wats.nextInt();
		remainder = num1%num2;
		System.out.println(remainder);
	}
}
