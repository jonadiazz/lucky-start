
/*
Name: Jonathan Vasquez
Class : 1620-003 
Program # : 04 
Due Date : April 3 / 2014 

Honor Pledge: On my honor as a student of the University 
of Nebraska at Omaha, I have neither given nor received 
unauthorized help on this homework assignment. 
Signed: Jhonatan Vasquez, 88139896, jvasquez@unomaha.edu 

Collaborators : Tutoring Center

Description: The equation evaluator is the class that is responsible to deal with the convertion of any input if possible to change to a postfix form. Also, it evaluates it. 
*/


import java.util.Scanner;
import ListPkg.Stack;
import ExceptionPkg.EmptyListException;

public class EquationEvaluator
{
    public StringBuffer convertToPostfix()
    {
        //object declarations needed for this method
        boolean foundError = false; // what I need to return for this method
        Scanner input = new Scanner(System.in);
        StringBuffer infixStringB = new StringBuffer();
        Stack<Character> stackOfChars = new Stack<Character>();
        StringBuffer postfix = new StringBuffer();

        System.out.println("\nEnter an expression in infix notation for evaluation\n");
        //takes a String from the keyboard and appends it to the infixStringBuffer
        String keyboard = input.nextLine();
        infixStringB.append(keyboard);
        stackOfChars.push('(');
        infixStringB.append(')');

        //while loop that checks every char of the StringBuffer infixStringB
        //depending on the char, it will send it to the StackofChars or the postfix expression for later use
        int pos = 0;
        while(pos != infixStringB.length() && foundError == false)
        {
            char atPosition = infixStringB.charAt(pos);
            if( Character.isDigit(atPosition))
            {
                postfix.append(atPosition);
                postfix.append(' '); 
            }
            else if( !Character.isDigit(atPosition))
            {
                if(atPosition == ' ')
                {
                }
                else if( atPosition == '(')
                    stackOfChars.push('(');
                else if ( atPosition == ')')
                {
                    while(stackOfChars.peek() != '(') //openFound == false) // || stackOfChars.isEmpty()) //until a '(' is popped 
                    {


                        postfix.append(stackOfChars.pop());
                    }
                    stackOfChars.pop();
                    //if the stack is now empty:
                    if(stackOfChars.isEmpty() && pos != infixStringB.length()-1)
                    {
                        System.out.println("\nUnmatched parenthesis in expression");
                        foundError = true;
                        return null;
                    }
                    //  report 'mismatched parenthesis error
                }
                else if (atPosition == '^' || atPosition == '+' || atPosition == '-' || atPosition == '*' || atPosition == '/') 
                {
                    //pop operators off stack and append to output until operator 
                    //with lower precedence is on top of stack
                    while (!stackOfChars.isEmpty() && stackOfChars.peek() != '(' && !hasPrecedence(atPosition, stackOfChars.peek())) 
                    {
                        postfix.append(stackOfChars.pop()); //offTheStack);
                        //operator with lower precedence is on top OR end of stack 
                    }
                    stackOfChars.push(atPosition);
                }
                else
                {
                    System.out.println("\nInvalid operator: use +-*/^");
                    System.out.println("Aborting expression evaluation\n");
                    foundError = true;
                    return null;
                }
            }
            pos++;
        }
        if(!stackOfChars.isEmpty())
        {
            System.out.println("\nUnmatched parenthesis in expression");
            return null;
        }

        System.out.println("\nYour expression in postfix is: " + postfix);
        return postfix;
    }

    //evaluates the postfix expression obtained from the convertion if any
    public boolean evaluatePostfix(StringBuffer postfix)
    {
        //need to declare a stack to hold the result of the postfix
        //since the stack uses generics, we specify to use Double
        Stack<Double> doubleValues = new Stack<Double>();
        double result = 0;
        int pos = 0;
        //while not at the end of the expression reached and no error has occurred
        while(postfix.length() != pos)
        {
            char charAtPos = postfix.charAt(pos);
            if(charAtPos == ' ')
            {
            }
            else if(Character.isDigit(charAtPos))
            {
                Double value = (double) Character.digit(charAtPos, 10);
                doubleValues.push(value);
            }
            //if char is not a digit
            else if(!Character.isDigit(charAtPos))
            {
                if(charAtPos == '+' || charAtPos == '-' || charAtPos == '*' || charAtPos == '/')
                {
                    //switch for every posible operation cases
                    double leftOperand = doubleValues.pop();
                    double rightOperand = doubleValues.pop();
                    switch(charAtPos) 
                    {
                        case '*':
                            result = rightOperand*leftOperand;
                            break;
                        case '/':
                            if (leftOperand==0)
                            {
                                System.out.println("Cannot divide by zero");

                                System.out.println("Aborting expression evaluation\n");
                                return false;
                            }       
                            result = rightOperand/leftOperand;
                            break;
                        case '+':
                            result = rightOperand+leftOperand;
                            break;
                        case '-':
                            result = rightOperand-leftOperand;
                            break;
                        case '^':
                            result = Math.pow(rightOperand, leftOperand);
                            break;
                        default:
                            result = 0;
                    }
                    doubleValues.push(result);
                }
            }
            pos++;
        }
        result = doubleValues.pop();
        if(doubleValues.isEmpty())
        {
            System.out.printf("\tResult: %.2f\n\n", result);
        }
        else
        {
            System.out.println("Invalid number of operators");
            System.out.println("Aborting expression evaluation\n");
            return false;
        }
        return true;
    }

    //compares the two chars passed, if the first has higher precedence returns true, else returns false.
    private boolean hasPrecedence(char op1, char op2)
    {
        if(op1== '^' && op2 != '^')
            return true;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return true;
        else 
            return false;
    }
}

