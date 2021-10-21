package adamc;

/**
 * A simple class that allows our program to communicate with the terminal and print errors and
 * values of variables to it.
 * This class may be updated if I add a GUI to the interpreter in the future.
 */
public class Console {

  /**
   * Prints an error to the system terminal.
   *
   * @param errorMessage the message to be printed.
   * @param line         the instruction that the error occurred on.
   */
  public void printError(String errorMessage, int line) {
    System.err.println("Error at line " + line + "\n" + errorMessage);
  }

  /**
   * Prints a variables value to the system terminal
   *
   * @param varName the name of the variable to be printed.
   * @param value   the value of the variable to be printed.
   * @param line    the instruction that this value was printed on.
   */
  public void printVariable(String varName, int value, int line) {
    System.out.println("Value of " + varName + " = " + value + ", Instruction line " + line);
  }
}
