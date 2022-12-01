import java.util.*;



public class MyClass {

    public static void main(String args[]) {

      Scanner sc = new Scanner(System.in);

      String _n1 = sc.next();

      String plus = sc.next();

      String _n2 = sc.next();

      try{

          int n1 = Integer.parseInt(_n1);

          int n2 = Integer.parseInt(_n2);

          System.out.println("Answer: "+(n1+n2));

      }

      catch(Exception ex){

          StackTraceElement[] callStack = ex.getStackTrace();

          /*

          ex.printStackTrace();

          java.lang.NumberFormatException: For input string: "2o"

            	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)

            	at java.base/java.lang.Integer.parseInt(Integer.java:668)

            	at java.base/java.lang.Integer.parseInt(Integer.java:786)

            	at MyClass.main(MyClass.java:11)

          */

          //callStack[0] -> info for line t which Exception was found in Integer.parseint(int val) method

          int lineNo = callStack[callStack.length - 1].getLineNumber();

          if(lineNo == 10){

              System.out.println("Number1 is not of integer type.");

              System.out.println("Enter a number instead of \""+_n1+"\".");

          }else if(lineNo == 11){

              System.out.println("Number2 is not of integer type.");

              System.out.println("Enter a number instead of \""+_n2+"\".");

          }

          

      }

      System.out.println("Thank You");

    }

}
