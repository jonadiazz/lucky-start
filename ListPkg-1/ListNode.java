
/*
Name: Jonathan Vasquez
Class : 1620-003 
Program # : 00 
Due Date : 18 March / 2014 

Honor Pledge: On my honor as a student of the University 
of Nebraska at Omaha, I have neither given nor received 
unauthorized help on this homework assignment. 
    Signed: Jhonatan Vasquez, 88139896, jvasquez@unomaha.edu 

Collaborators : Tutoting Center 

Description: This class sets the nodes of the LinkedList structure. It is able to create modify and handle a few tasks for this nodes.

 */


package ListPkg;

public class ListNode<T> 
{
    //data members
    private T data;
    private ListNode<T> nextNode;

    //constructor ListNode that passes an object
    public ListNode (T object)
    {
        this(object, null);
    }
    //overloaded constructor that passes an object that is assigned to data and a node of type ListNode for the nextNode
    public ListNode (T object, ListNode<T> node)
    {
        data = object;
        nextNode = node;
    }
    //sets new data for the member data
    public void setData (T object)
    {
        data = object;
    }
    //returns the data of type T
    public T getData ()
    {
        return data;
    }
    //sets the new node for the next node
    public void setNext (ListNode<T> next)
    {
        nextNode = next;
    }
    //returns the next Node
    public ListNode<T> getNext()
    {
        return nextNode;
    }
}
