class Queue<Q>{	
	class Node{
		Q o;
		Node next;
		Node(Q obj){
			o = obj;
			next = null;
		}
	}
	Node front, back;
	void enqueue(Q obj){
		Node n = new Node(obj); 
		if (front == null){
			front = n;
			back = n;		
		}else{
			back.next = n;
			back = n;
		}
	}
	Q dequeue(){
		if (front == null){
			System.out.println("The Queue is empty!!");
			return null;
		}
		Node obj = front;
		front =  front.next;
		return obj.o;
	}	
	void printContent(){
		Node n = front;
		System.out.print(" The contents of the queue are :\t");
		while(n!= null){
			System.out.print(n.o+"\t");
			n = n.next;
		}
		System.out.println();
	}
}

class sortedQueue{
	
	static <T> void test(T[] arr){
		Queue<T> q = new Queue<T>();
		for(int i=0; i<arr.length; i++){
			q.enqueue(arr[i]);
		}
		q. printContent();
		//T o = q.dequeue();
		//while (o!= null){
			//System.out.println(o);
			//o = q.dequeue();
		//}
	}
	class Sorting
	{
    public Double[] test_arr1;
	public Object[] arr;
	Sorting(Integer i[])
    {
		test_arr1=new Double[i.length];
		arr=new Integer[i.length];
		for(int j=0;j<i.length;j++)
		{
			arr[j]=i[j];
			test_arr1[j]=(double)i[j];
		}
	 }
	 Sorting(Double i[])
	 {
		 test_arr1=new Double[i.length];
		 arr=new Double[i.length];
		for(int j=0;j<i.length;j++)
		{
			arr[j]=i[j];
			test_arr1[j]=i[j];
		}
	 }
	 Sorting(Character i[])
	 {
		 test_arr1=new Double[i.length];
		 arr=new Character[i.length];
		for(int j=0;j<i.length;j++)
		{
			arr[j]=i[j];
			int x=(int)i[j];
			test_arr1[j]=(double)i[j];
		}
	 }
	 Sorting(String i[])
	 {
		 test_arr1=new Double[i.length];
		 arr=new String[i.length];
		for(int j=0;j<i.length;j++)
		{
			arr[j]=i[j];
			String s=i[j];
			Double d=0.0;
			for(int k=0;k<s.length();k++)
			{
				d=d+Math.pow(255,-(k+1))*s.charAt(k);
			}
			test_arr1[j]=(double)d;
		}
	 }
    public void Quicksort() 
    {
        if (arr ==null || test_arr1.length==0){
                return;
        }
        quicksort(0, arr.length - 1);
    }
    public void quicksort(int low, int high)
    {
        int i = low, j = high;
        double pivot = test_arr1[low + (high-low)/2];
        while (i <= j) {
                while (test_arr1[i] < pivot) {
                        i++;
                }
                while (test_arr1[j] > pivot) {
                        j--;
                }
                if (i <= j) {
                        exchange(i, j);
                        i++;
                        j--;
                }
        }
        if (low < j)
                quicksort(low, j);
        if (i < high)
                quicksort(i, high);
    }
    public void exchange(int i, int j)
    {
        Double temp = test_arr1[i];
        test_arr1[i] = test_arr1[j];
        test_arr1[j] = temp;
		Object tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
    }
    public void  print()
    {
        //{
            System.out.println("The Elements of sorted queue : ");
            for(int j=0;j<test_arr1.length;j++)
            {
                System.out.print(test_arr1[j]+"  ");
            }
            System.out.println();
        //}
    }
}
	public static void main(String [] args){
		Integer[] intArray = { 3, 4, 2, 1, 5 };
		Double[] doubleArray = { 5.1, 2.2, 3.2, 1.4 };
      	Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
      	String[] stringArray = {"qw", "we", "er", "rt", "ty"};
		test(intArray);
		test(doubleArray);
		test(charArray);
		test(stringArray);		
		sortedQueue sq=new sortedQueue();
		sortedQueue.Sorting s1=sq.new Sorting(intArray);
		sortedQueue.Sorting s2=sq.new Sorting(doubleArray);
		sortedQueue.Sorting s3=sq.new Sorting(charArray);
		sortedQueue.Sorting s4=sq.new Sorting(stringArray);
		s1.Quicksort();
		s2.Quicksort();
		s3.Quicksort();
		s4.Quicksort();
		s1.print();
		s2.print();
		s3.print();
		s4.print();
	}
}