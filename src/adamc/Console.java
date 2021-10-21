package adamc;

public class Console {

  public void printError(String errorMessage, int line) {
    System.out.println("Error at line " + line + "\n" + errorMessage);
  }

  public void printVariable(String varName, int value, int line) {
    System.out.println("Value of " + varName + " = " + value + ", Instruction line " + line);
  }
}
