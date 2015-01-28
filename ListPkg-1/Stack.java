
/*
Name: Jonathan Vasquez
Class : 1620-003 
Program # : 04 
Due Date : April 3 / 2014 

Honor Pledge: On my honor as a student of the University 
of Nebraska at Omaha, I have neither given nor received 
unauthorized help on this homework assignment. 
    Signed: Jhonatan Vasquez, 88139896, jvasquez@unomaha.edu 

Collaborators : NONE

Description: This class creates an Stack. This stack holds the methods that makes possible to pop, push, peek and check if the stack is empty from where is created.
 */


package ListPkg;

//declaring class Stack of type generic
public class Stack<T>
{
    //declare the list that will hold the information of the object in the stack
    private LinkedList<T> list;
    //constructors
    public Stack()
    {
        this("list");
    }
    public Stack(String name)
    {
        list = new LinkedList(name);
    }
    //pushes a new object into the stack
    public void push(T item)
    {
        list.insertAtFront(item);
    }
    //pops a new object from the stack
    public T pop()
    {
        return list.removeFromFront();
    }
    public int lenghtIs()
    {
        return list.lengthIs();
    }
    //checks the top item from the stack
    public T peek()
    {
        return list.returnFirstNode();
    }
    public void print()
    {
        list.print();
        //printStack(this);
    }
    //checks if the stack is empty
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
}
