//Java program to implement a catalogue for books and notes
import java.io.*;
import java.util.*;

//Definition of book class

class Book{
    //length breadth width
    private int l,b,w;

    //Name of the book
    private String name;

    //Weight of the book
    private int weight;

    //index of each book mapped to its name
    private int index;  

    //Function to set dimensions of the book
    public void setDimensions(int length , int breadth , int width){
        this.l = length;
        this.b = breadth;
        this.w = width;
        this.weight = l*b*w*2;
    }

    //Function to return the weight of the book
    public int getWeight(){
        return this.weight;
    }

    //Function to set name of the book
    public void setName(String name){
        this.name = name;
    }

    //Function to return the name of the book
    public String getName(){
        return this.name;
    }

    //Function to set the index
    public void setIndex(int index){
        this.index = index;
    }
    
    //Function to return index of the book
    public int getIndex(){
        return this.index;
    }
}

//Definition of Notes class

class Notes{
    //Number of pages
    private int numOfPg;
    
    //Name of the notes
    private String name;

    //Index of the notes
    private int index;

    //Function to set the number of pages of the notes
    public void setDimensions(int num){
        this.numOfPg = num;
    }   

    //Function to returtn the number of page
    public int getPage(){
        return this.numOfPg;
    }

    //Function to set name of notes
    public void setName(String name){
        this.name = name;
    }

    //Functiont to return the name of the notes
    public String getName(){
        return this.name;
    } 

    //Function to set the index
    public void setIndex(int index){
        this.index = index;
    }

    //Function to return the index of the notes
    public int getIndex(){
        return this.index;
    }  
}

/**
 * INterface for catalogue
 */

interface Catalogue {

    //Function to return the weight of book
    int weight(Book b);

    //Function to return the number of pages for the notes
    int weight(Notes n);

    //Function to sort the books on the basis of their weights
    void sort(Book b[] , int size);

    //Function to sort the notes on the basis of the number of pages they contain
    void sort(Notes n[], int size);

    //Function to search a book from the catalogue
    int search(Book b[] , int size , String name);

    //Function to search a notes from the catalogue
    int search(Notes b[] , int size , String name);
}

//Implementer class for catalogue

class X implements Catalogue{

    //Functtion to return the weight of the book
    public int weight(Book b){
        return b.getWeight();
    }

    //Function to return the number of pages of the notes
    public int weight(Notes n){
        return n.getPage();
    }

    //Function to sort the books on the basis of their weights
    //Bubble sort logic
    public void sort(Book b[], int size){
        for(int i = 0 ; i < size ; i++){
            for(int j = 1 ; j < size - i ; j++){
                if(weight(b[j]) < weight(b[j-1])){
                    Book temp = b[j];
                    b[j] = b[j-1];
                    b[j-1] = temp;    
                }    
            }
            b[size - 1 - i].setIndex(size - i);
        }
    }

    //Function to sort the notes on the basis of the number of pages they contain
    //Bubble sort logic
    public void sort(Notes n[], int size){
        for(int i = 0 ; i < size ; i++){
            for(int j = 1 ; j < size - i ; j++){
                if(weight(n[j]) < weight(n[j-1])){
                    Notes temp = n[j];
                    n[j] = n[j-1];
                    n[j-1] = temp;    
                }    
            }
            n[size - 1 - i].setIndex(size - i);
        }
    }

    //Function to search a book from catalogue
    public int search(Book b[] , int size , String name){
        int index = 0;
        for(int i = 0 ; i < size ; i++)
            if(name.equals(b[i].getName()))
               index = b[i].getIndex();
        return index;
    } 

    //Function to search notes from the catalogue
    public int search(Notes n[] , int size , String name){
        int index = 0;
        for(int i = 0 ; i < size ; i++)
            if(name.equals(n[i].getName()))
                index = n[i].getIndex();
        return index;
    }
}

class BookCat{
    //Main Function
    public static void main(String args[])throws IOException{
        X obj = new X();
        Scanner wats = new Scanner(System.in);
        System.out.println("Enter the number of books and notes");
        int bSize = wats.nextInt();
        int nSize = wats.nextInt();
        Book b[] = new Book[bSize];
        Notes n[] = new Notes[nSize];
        for(int i = 0 ; i < bSize ; i++){
            System.out.println("Enter the name of the Book");
            String name = wats.next();
            b[i] = new Book();
            b[i].setName(name);
            int length , breadth , width;
            System.out.println("Enter the length breadth and width of the book");
            length = wats.nextInt();
            breadth = wats.nextInt();
            width = wats.nextInt();
            b[i].setDimensions(length , breadth , width);
            b[i].setIndex(i);
        }
        for(int i = 0 ; i < nSize ; i++){
           System.out.println("Enter the name of the Notes");
            String name = wats.next();
            n[i] = new Notes();
            n[i].setName(name); 
            System.out.println("Enter the number of pages in the Notes");
            int nop = wats.nextInt();
            n[i].setDimensions(nop);
            n[i].setIndex(i);
        }
        obj.sort(b , bSize);
        obj.sort(n , nSize);
        System.out.println("Enter the name of the book you want to search");
        String name = wats.next();
        System.out.printf("Index of the book you searched is %d\n",obj.search(b , bSize , name));
        System.out.println("Enter the name of the notes you want to search");
        name = wats.next();
        System.out.printf("Index of the book you searched is %d\n",obj.search(n , nSize , name));
    }
}
 