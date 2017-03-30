import java.util.*;
import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
class EmployeeRecords{
   //For storing the details of employee 
   private String details;

   //Function for setting the details of Employee
   public void setDetails(String details){
       this.details = details;
   }

   //Function for fetching the details of the employee
   public String getDetails(){
        return this.details;
   }        

   //For storing the salary of employee
   private int salary;

   //Calling the default constructor
   public EmployeeRecords(){
       this.salary = 0;
   }

   //Function to set the salary of the Employee
   public void setSalary(){
       int i = details.length() - 1,flag = 1;
       int pos = 0;
       while(true){
            if(details.charAt(i) == ' '){
                pos = i + 1;
                break;
            }
            i--;
       }
       for(int j = pos; j < details.length(); j++){
           salary = salary * 10 + (details.charAt(j) - '0');
       }
   }
   public int getSalary(){
       return this.salary;
   }
}

class Employee{
    public static void main(String args[])throws IOException{
        EmployeeRecords emp[] = new EmployeeRecords[5];
        FileReader fr = new FileReader("record.txt");
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        String input = br.readLine();
        while(input != null){
            emp[i] = new EmployeeRecords();
            emp[i].setDetails(input);
            emp[i].setSalary();
            i++;
            input = br.readLine();   
        }
        //Function to sort the records on the basis of salary
        for(int j = 0 ; j < i ; j++){
            for(int k = 1 ; k < i - j ; k++){
                if(emp[k].getSalary() < emp[k-1].getSalary()){
                    EmployeeRecords temp = new EmployeeRecords();
                    temp = emp[k];
                    emp[k] = emp[k -1];
                    emp[k - 1] = temp;
                }
            }
        }
        FileWriter fw = new FileWriter("new_record.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for(int j = 0 ; j < i ; j ++){
            bw.write(emp[j].getDetails());
            bw.write("\n");
        }
        bw.write("\n Updated by : Aman Kumar Wats");
        bw.close();
    }
}