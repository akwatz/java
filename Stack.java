//CLASS STACK USING LINKED LIST
class Node<T>{

  public T data;
  public Node next;
}

class Stack<T>{
  private Node top;
  public void push(T val){
    System.out.println("Element pushed is " + val);
      if(top==null)
      {
        top=new Node();
        top.data=val;
        return;
      }
      Node temp=new Node();
      temp.data=val;
      temp.next=top;
      top=temp;
  }

  public void pop(){
    if(top!=null)
    {
        if(this.top==null)
          {
            System.out.println("STACK IS EMPTY");
            return;
          }
          System.out.println("The popped item is " + top.data);
          top=top.next;
    }
  }

  public void top(){
    if(!top){
      System.out.println("Stack is EMPTY");
      return;
    }
    System.out.println("The top element is "top.data);
  }

  public void print(){
    if(top!=null)
    {
      Node temp=this.top;
      while(temp!=null)
      {
        System.out.print(temp.data+" ");
        temp=temp.next;
      }
      System.out.print("\n");
    }
  }

  public Stack ShallowCopy(){
    
    return this;
  }

  public Stack DeepCopy(Stack){
    Stack temp = this.clone();
    return temp;
  }
}

public class StackImplement{
  public static void main(String[] args){
    Stack obj=new Stack();
    System.out.println("Character Stack");
    obj.push('E');
    obj.push('D');
    obj.push('C');
    obj.push('B');
    obj.push('A'); 
    obj.print();
    obj.pop();
    obj.pop();
    obj.print();
    obj=new Stack();
    System.out.println("Integer Stack");
    obj.push(5);
    obj.push(4);
    obj.push(3);
    obj.print();
    obj.push(2);
    obj.push(1); 
    obj.print();
    obj.pop();
    obj.pop();
    obj.print();
    obj=new Stack();
    System.out.println("Floating Point Stack");
    obj.push(0.5f);
    obj.push(0.4f);
    obj.push(0.3f);
    obj.print();
    obj.push(0.2f);
    obj.push(0.1f); 
    obj.print();
    obj.pop();
    obj.pop();
    obj.print();
    obj=new Stack();
    System.out.println("String Stack");
    obj.push("Wats");
    obj.push("Kumar");
    obj.push("Aman");
    obj.print();
    obj.push("NAME");
    obj.push("MY"); 
    obj.print();
    obj.pop();
    obj.pop();
    obj.print();

    //this is for checking shallowcopy
    System.out.println("This checks for Shallow Copy");
    Stack temp1=new Stack();         
    temp1=obj.ShallowCopy();
    temp1.print();
    temp1.push("qwerty");
    obj.print();


    //this is for checking Deepcopy;
    System.out.println("This Checks for Deep Copy");
    Stack temp2=new Stack();
    temp2=obj.DeepCopy();
    temp2.print();
    temp2.push("qwerty");
    temp2.print();
    obj.print();
  }
}