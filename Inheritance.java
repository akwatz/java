import java.io.*;
import java.util.*;
class Memory{
    protected static boolean[] occupied = new boolean[100];
    protected String decimalToHex(int num){
        // For storing remainder
        int rem;
        
        // For storing result
        String str2=""; 
        
        // Digits in hexadecimal number system
        char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        
        //Converting decimal to hexadecimal
        while(num>0){
            rem=num%16; 
            str2=hex[rem]+str2; 
            num=num/16;
        }

        //Returning the result
        return str2;
    }
}
class Calculations extends Memory{
    public Scanner wats = new Scanner(System.in);

    //Function to set Memory
    public void setMemory(){
        System.out.println("Enter your choice");
        System.out.println("1. String");
        System.out.println("2. Integer");
        int choice = wats.nextInt();
        int freePos = 0;
        switch (choice) {
            case 1:
                setString();
                break;
            case 2 :
                setInt();
                break;
            default:
                break;
        }
    }

    //Function to set string
    void setString(){
        System.out.println("Enter the position");
        int pos = wats.nextInt();
        System.out.println("Provide your String");
        String str = wats.next();
        int length = str.length();
        int freePos = -1;
        freePos = isString(pos , length);
        if(freePos == 0)
            System.out.println("Allocation Done");
        else
            System.out.println("Memory is not free , Next free space is at location " + decimalToHex(freePos));
    }

    //Function to set int
    void setInt(){
        System.out.println("Enter the position");
        int pos = wats.nextInt();
        System.out.println("Enter you integer");
        int data = wats.nextInt();
        int freePos = -1;
        freePos = isInt(pos);
        if(freePos == 0)
            System.out.println("Allocation Done");
        else    
            System.out.println("Memory is not free , Next free space is at location " + decimalToHex(freePos));
    }

    //Function to tell if the given integer can be allocated the given Memory
    int isInt(int pos){
        int flag = 0;
        for(int i = pos ; i < pos + 4 ; i++)
            if(occupied[i] == true)
                flag = 1;
        if(flag == 0){
            for(int i = pos ; i < pos + 4 ; i ++)
                occupied[i] = true;
            return flag;
        }
        for(int i = pos ; i < 100 - pos ; i++)
            if(occupied[i] == false)
                return i;
        System.out.println("yes");
        return 0;
    }

    //Function to tell if the given string can be alloted the given memory
    int isString(int pos , int length){
        int flag = 0;
        for(int i = pos ; i < pos + 2*length ; i++)
            if(occupied[i] == true)
                flag = 1;
        if(flag == 0){
            for(int i = pos ; i < pos + 2*length ; i++)
                occupied[i] = true;
            return flag;
        }
        for(int i = pos ; i < 100 - pos ; i++)
            if(occupied[i] == false)
                return i;
        return 0;
    }
}
class Inheritance extends Calculations{
    public static void main(String args[])throws IOException{
        Inheritance obj1 = new Inheritance();
        Inheritance obj2 = new Inheritance();
        obj1.setMemory();
        obj2.setMemory();
    }
}