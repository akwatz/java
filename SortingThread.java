import java.util.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CountDownLatch;
class Sorting{
	//Function to do QuickSort
	public void QuickSort(double arr[] , int start , int end){
		if(start >= end)
			return;
		int pIndex = getPivot(arr , start , end);
		QuickSort(arr , start , pIndex - 1);
		QuickSort(arr , pIndex + 1 , end);
	}

	//Function to perform partitioning
	public int getPivot(double arr[] , int start , int end){
 		double pivot = arr[start];
		int pIndex = start + 1;
		for(int i = pIndex ; i <= end; i++){
			if(arr[i] < pivot){
				double temp = arr[i];
				arr[i] = arr[pIndex];
				arr[pIndex] = temp;
				pIndex += 1;
			}
		}
		double temp = arr[start];
		arr[start] = arr[pIndex - 1];
		arr[pIndex -1] = temp;
		return (pIndex - 1);
	}

	//Function to perform merge sort
	public void MergeSort(double arr1[] , int n){
		if(n < 2)
			return;
		double left[] = new double[n/2];
		double right[] = new double[n - n/2];
		for(int i = 0 ; i < n/2 ; i++)
			left[i] = arr1[i];
		for(int i = 0 ; i < n - n/2 ; i++)
			right[i] = arr1[n/2 + i];
		MergeSort(left , n/2);
		MergeSort(right , n - n/2);
		Merge(left , right , n/2 , n - n/2 , arr1);
	}

	//Function to merge two sorted arrays
	public void Merge(double left[] , double right[] , int lSize , int rSize , double arr[]){
		int i = 0 , j = 0 , k = 0;
		while(i < lSize && j < rSize){
			if(left[i] < right[j]){
				arr[k] = left[i];
				i += 1;
				k += 1;
			}
			else{
				arr[k] = right[j];
				j += 1;
				k += 1;
			}
		}
		while(i < lSize){
			arr[k] = left[i];
			i += 1;
			k += 1;
		}
		while(j < rSize){
			arr[k] = right[j];
			j += 1;
			k += 1;
		}
	}

	//Function to generate and fill random numbers in an array
	void generator(double arr[]){
		for(int i = 0 ; i < 100 ; i++)
			arr[i] = (Math.random() * 99.0 + 0.0);
	}
}
class ThreadImplementer{
	final AtomicInteger winner = new AtomicInteger(0);
	private boolean isComplete;
	ThreadImplementer(){
		isComplete = false;
		mergeSortThread m = new mergeSortThread();
		quickSortThread q = new quickSortThread();
		m.start();
		q.start();
	}

	public class mergeSortThread extends Thread{
		private double arr[] = new double[100];
		Sorting s = new Sorting();
		mergeSortThread(){
			s.generator(arr);
		}
		public void run(){
			s.MergeSort(arr , 100);
			synchronized(this){
			if(!isComplete){
				isComplete = true;
				System.out.println("Merge Sort Completed First");
				System.out.println("The Sorted Output merge sort is ");
				for(int i = 0 ; i < 100 ; i++)
					System.out.printf("%.2f  ",arr[i]);
				System.out.println();
			}
			/*else{
				System.out.println("The Sorted Output of merge sort is ");
				for(int i = 0 ; i < 100 ; i++)
					System.out.printf("%.2f  ",arr[i]);
				System.out.println();
			}*/
			}
		}
	}

	class quickSortThread extends Thread{
		private double arr[] = new double[100];
		Sorting s = new Sorting();
		quickSortThread(){
			s.generator(arr);
		}
		public void run(){
			s.QuickSort(arr , 0 , 99);
			synchronized(this){
			if(!isComplete){
				isComplete = true;
				System.out.println("Quick Sort Completed First");
				System.out.println("The Sorted Output of quick sort is ");
				for(int i = 0 ; i < 100 ; i++)
					System.out.printf("%.2f  ",arr[i]);
				System.out.println();
			}
			/*else{
				System.out.println("The Sorted Output quick sort is ");
				for(int i = 0 ; i < 100 ; i++)
					System.out.printf("%.2f  ",arr[i]);
				System.out.println();
			}*/
			}
		}
	}
}
class SortingThread{
	public static void main(String args[]){
		/*double arr[] = new double[10];
		Random r = new Random();
		for(int i = 0 ; i < 10 ; i++)
			arr[i] = (0.0 + 99.9 * r.nextDouble());
		Sorting s = new Sorting(arr);
		s.QuickSort(arr , 0 , 9);
		for(int i = 0 ; i < 10; i++)
			System.out.printf("%.2f \t",arr[i]);
		System.out.println();*/
		ThreadImplementer t = new ThreadImplementer();
	}
}