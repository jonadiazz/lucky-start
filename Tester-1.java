// filename: Tester.java
// Written by: Sandra Vlasnik - 10-22-2012
// Creates an object of type EquationEvaluator to
// convert an infix express to postfix format then
// evaluate the postfix expression and print the result

import java.util.Scanner;

public class Tester {

    public static void main (String args[]) {

        // create the EquationEvaluator object
        EquationEvaluator equation = new EquationEvaluator();
        StringBuffer postfix = null;
        boolean errorInEquation;

        // repeat:
        //    Get the infix equation and convert to postfix
        //    if equation converts
        //       evaluate the equation and show results
        //    if equation contains errors 
        //       print an error message
        // until: equation is successfully evaluated
        do{
            errorInEquation = false;
            postfix =  equation.convertToPostfix();
            if (postfix != null){
                if(!equation.evaluatePostfix(postfix))
                    errorInEquation = true;
            }
            else{
                errorInEquation = true;
            }
            if (errorInEquation)
                System.out.println ("\tYour expression was invalid - Please try again");

        } while (errorInEquation);
    }
}
