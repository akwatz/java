//CLASS STACK USING LINKED LIST
class Node<T>
{

  public T data;
  public Node next;
}
class Stack<T>
{
  private Node top;
  public void push(T val){
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
          top=top.next;
    }
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
  public Stack DeepCopy(){
    Stack temp=new Stack();
    temp.top=this.top;
    return temp;
  }
}
public class A2Q4
{
  public static void main(String[] args){
    Stack obj=new Stack();
    obj.push('E');
    obj.push('D');
    obj.push('C');
    obj.push('B');
    obj.push('A'); 
   // obj.print();
    obj.pop();
    obj.pop();
    //obj.print();
    obj=new Stack();
    obj.push(5);
    obj.push(4);
    obj.push(3);
    //obj.print();
    obj.push(2);
    obj.push(1); 
    //obj.print();
    obj.pop();
    obj.pop();
    //obj.print();
    obj=new Stack();
    obj.push(0.5f);
    obj.push(0.4f);
    obj.push(0.3f);
    //obj.print();
    obj.push(0.2f);
    obj.push(0.1f); 
    //obj.print();
    obj.pop();
    obj.pop();
    //obj.print();
    obj=new Stack();
    obj.push("RATHORE");
    obj.push("SINGH");
    obj.push("RAHUL");
    //obj.print();
    obj.push("NAME");
    obj.push("MY"); 
    //obj.print();
    obj.pop();
    obj.pop();
    //obj.print();

    //this is for checking shallowcopy
    System.out.println("this is for checking Shallowcopy");
    Stack temp1=new Stack();         
    temp1=obj.ShallowCopy();
    temp1.print();
    temp1.push("qwerty");
    obj.print();


    //this is for checking Deepcopy;
    System.out.println("this is for checking Deepcopy");
    Stack temp2=new Stack();
    temp2=obj.DeepCopy();
    temp2.print();
    temp2.push("qwerty");
    temp2.print();
    obj.print();
  }
}