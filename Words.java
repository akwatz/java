import java.io.*;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
class Words{
    public static void main(String args[]) throws IOException{
        //To store properly arranged words
        String properWords[] = new String[30];
        
        //To store jumbled words
        String jumbledWords[] = new String[30];

        //Variable for reading from file
        FileReader fr = new FileReader("jumbles.txt");
        BufferedReader br = new BufferedReader(fr);
        int i = 0;

        //Storing the input from buffer
        while((jumbledWords[i++] = br.readLine() )!= null);
        fr = new FileReader("arranged.txt");
        br = new BufferedReader(fr);
        i = 0;
        while((properWords[i++] = br.readLine() )!= null);
        boolean wordsFound[] = new boolean[i];

        //Searching for the words
        for(int j = 0 ; j < i - 1 ; j++){

            //Storing a single jumbled word in a temporary Variable
            String temp = jumbledWords[j];
            for(int k = 0 ; k < i - 1 ; k++){

                //If the current word is already found then don't proceed further
                if(wordsFound[k])
                    continue;

                //Storing a single properly arranged word in temporary Variable
                String temp2 = properWords[k];

                //If the jumbled word and proper word vary in length then dont't proceed furthur
                if(temp.length() != temp2.length())
                    continue;

                //Creating a visited array to check if all the letters of the word have been discovered
                boolean visited[] = new boolean[temp.length()];
                for(int l = 0 ; l < temp.length() ; l++){

                    //Creating a flag variable to tell if a letter from
                    //jumbled word is found or not in proper word
                    int flag = 0;

                    //Checking each letter of proper word for jumbled word
                    for(int m = 0 ; m < temp.length() ; m++){
                        if(temp.charAt(l) == temp2.charAt(m) && !visited[m]){
                            visited[m] = true;
                            flag = 1;
                            break;
                        }
                    }

                    //If the letter is not found then break from this loop
                    if(flag == 0)
                        break;
                    flag = 0;
                }

                //Setting the flag variable zero again
                int flag = 0;

                //Checking if all the letters have been visited or not
                for(int l = 0 ; l < temp.length() ; l++)
                    if(!visited[l])
                        flag = 1;
                //If all the words have been visited then this is the 
                //proper word for given jumbled word
                //Printing both jumbled and corresponding proper word
                if(flag == 0){
                    System.out.println();
                    System.out.println("The Jumbled Word is     " + temp);
                    System.out.println("The Proper Word is      " + temp2);
                    System.out.println();

                    //Setting the given proper word as found
                    wordsFound[k] = true;
                    break;
                }
            }
        }
    }
}