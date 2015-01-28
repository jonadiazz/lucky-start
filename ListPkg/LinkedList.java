
/*
Name: Jonathan Vasquez
Class : 1620-003 
Program # : 00 
Due Date :  18 March / 2014 

Honor Pledge: On my honor as a student of the University 
of Nebraska at Omaha, I have neither given nor received 
unauthorized help on this homework assignment. 
    Signed: Jhonatan Vasquez, 88139896, jvasquez@unomaha.edu 

Collaborators : Tutoring Center

Description: Implements the LinkedList dynamic data structures. This is the LinkedList that holds the nodes.
 */


package ListPkg;
import ExceptionPkg.EmptyListException;

public class LinkedList <T>// extends Comparable<T>> //extends Comparable<LinkedList<T>>       //`extends EmptyListException 
{
    //private data members
    private ListNode<T> firstNode;
    private ListNode<T> lastNode;
    private Integer numElements;
    private String name;

    //constructor giving default name "list"
    public LinkedList()
    {
        this("list");
    }
    //constructor overloaded 
    public LinkedList(String listName)
    {
        firstNode = null;
        lastNode = null;
        numElements = 0;
        name = listName;
    
    }
    //inserts an item passed to the front of the LinkedList
    public void insertAtFront(T item)
    {
        ListNode<T> newNode;
        if(firstNode == null)
        {
            newNode = new ListNode<T> (item, null);
            firstNode = lastNode = newNode;
        }
        else
        {
            newNode = new ListNode<T> (item, firstNode);
            firstNode = newNode;
        }
        numElements++;
    }
    //inserts an item passed to the back of the LinkedList
    public void insertAtBack(T item)
    {
        if(lastNode == null)
        {
            firstNode = lastNode = new ListNode<T> (item);
        }
        else 
        {
            lastNode.setNext(new ListNode<T> (item));
            lastNode = lastNode.getNext();
            //lastNode = lastNode.nextNode = new ListNode<T> (item);
        }
        numElements++;
    }
    //public static <T> void  removeFromFront() throws EmptyListException
    public T removeFromFront() throws EmptyListException
    {
        if (isEmpty())
            throw new EmptyListException(name);
        T removedItem = firstNode.getData();

        //if this was the only node - set first/last to null
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else
            firstNode = firstNode.getNext();
            //firstNode = firstNode.nextNode;
        numElements--;
        return removedItem;
    }
    //removes an object from the Back of the list
    //public static <T> void removeFromBack() throws EmptyListException
    public T removeFromBack() throws EmptyListException
    {
        if (isEmpty())
            throw new EmptyListException (name);
        T removedItem = lastNode.getData();
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else
        { 
            //search for new last node (no reference is saved for 'next-to-last')
            ListNode<T> current = firstNode;
            while (current.getNext() != lastNode)
                current = current.getNext();
            lastNode = current;
            lastNode.setNext(null); // = null;
        }
        numElements--;
        return removedItem;
    }
    //this method removes an item at the position specified
    public void removeItemAt(Integer index) throws EmptyListException, IndexOutOfBoundsException
    {
        int counter = 0;
        ListNode<T> thisNode;
        if (isEmpty() == true)
        {
            throw new EmptyListException(name);
        } 
        else if (index >= numElements || index < 0)
        {
            throw new IndexOutOfBoundsException (name);
        } 
        else if (index == 0)
        {
            removeFromFront();
            //numElements++;
        }
        else
        {
            thisNode = firstNode;
            while (counter != index)
            { 
                thisNode = thisNode.getNext();
                counter++;
                if (counter == index-1)
                {
                    thisNode.setNext(thisNode.getNext().getNext());
                    numElements--;
                }
            }
        }
    }
    //returns the element at position passed
    public T getItemAt(Integer index) throws EmptyListException, IndexOutOfBoundsException
    {
        int counter = 0;
        ListNode<T> thisNode;
        if (isEmpty())
        {
            throw new EmptyListException(name);
        } 
        else if (index >= numElements || index < 0)
        {
            throw new IndexOutOfBoundsException (name);
        } 
        else
        {
            thisNode = firstNode;
            while (counter != index)
            { 
                thisNode = thisNode.getNext();
                counter++;
            }
        }

        return thisNode.getData();
    }
    //finds and removes an item passed and returns if it was able to find it and remove it or not
    public boolean findAndRemove(T item) throws EmptyListException
    {
        boolean found = false;
        if (!isEmpty())
        {
            //if item to remove is at the front (special case)
            //remove front item
            if ((firstNode.getData()).equals(item) == true) //0) //     compareTo(item) == 0)
            {
                removeFromFront();
                found = true;
            }
            //else look ahead to next node
                //search ahead to find item
                //if item found in next node
                //remove the next node
            else
            {
                ListNode<T> current = firstNode;
                while (current.getNext() != null && !found)
                {
                    if ((current.getNext().getData()).equals(item) == true) //0) //     compareTo(item)==0)
                        found = true;
                    else 
                        current = current.getNext();
                }
                if (found)
                {
                    if(current.getNext() == lastNode)
                        lastNode = current;
                    current.setNext(current.getNext().getNext());
                    numElements--;
                }
            }
        }
        else 
            throw new EmptyListException (name);
        return found;
    } 
    //finds the position of the item passed as paramenter and returns its position
    public Integer findItemPos(T item)
    {
        int counter = 0;
        boolean found = findItem(item);
        if (found == true)
        {
            boolean temp = false;
            ListNode<T> current = firstNode;
            while (current.getNext() != null && temp == false)
            {
                if (current.getNext().getData().equals(item) == true)
                {
                    temp = true;
                    counter++;
                }
                else
                {
                    counter++;
                    current = current.getNext();
                }
            }
        } 
        else 
        {
            counter = -1;
        }
        return counter;
    } 
    //chekcs if the item passed is in the LinkedList
    public boolean findItem(T item)
    {
        boolean found = false;
        if (!isEmpty())
        {
            ListNode<T> current = firstNode;
            while (current != null && !found)
            { 
                T theData = current.getData(); //.getObject();
                if (theData.equals(item)==true) //0) //        compareTo(item) == 0)
                    found = true;
                else
                    current = current.getNext();
            }
        }
        return found;
    }
    //returns the numElements
    public Integer lengthIs()
    {
        return numElements;
    }
    //clears up the data members from this class 
    public void clear()
    {
        numElements = 0;
        firstNode = lastNode = null;
    }
    //prints out if the list is empty, if not, prints an statement about the list
    public void print()
    {
        if(isEmpty())
            System.out.printf("\nEmpty %s\n", name);
        else 
        {
            System.out.printf("\nThe list %s is: ", name);
            ListNode<T> current = firstNode;

            while (current != null)
            {
                System.out.printf("%s ", current.getData());
                current = current.getNext();
            }
            System.out.println("\n");
        }
    }
    public boolean isEmpty()
    {
        return (firstNode == null || numElements == 0);
    }
    public T returnFirstNode()
    {
        return firstNode.getData();
    }
}
