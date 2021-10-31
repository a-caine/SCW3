package adamc;

import java.util.HashMap;

/**
 * A simple class that holds onto a map of variable names and the values associated with them.
 * Also contains a number of methods to communicate with said map.
 * Basically contains all of the instructions that bare bones can execute.
 */
public class VarHandler {
  private final HashMap<String, Integer> variables = new HashMap<>();

  /**
   * Checks whether the variable is contained within our map.
   *
   * @param varName the variable name that we are searching for.
   * @return true if we have a variable by that name, else will return false.
   */
  public boolean hasVariable(String varName) {
    return variables.containsKey(varName);
  }

  /**
   * Creates a variable and adds it to the map.
   *
   * @param varName the name that will be used to associate the variable with its value.
   */
  public void createVariable(String varName) {
    variables.put(varName, 0);
  }

  /**
   * Resets the value of the variable to zero.
   *
   * @param varName the name of the variable we are modifying.
   */
  public void clear(String varName) {
    variables.replace(varName, 0);
  }

  /**
   * Increases the value of the variable by 1.
   *
   * @param varName the name of the variable we are modifying.
   */
  public void incr(String varName) {
    variables.replace(varName, variables.get(varName) + 1);
  }

  /**
   * Decreases the value of the variable by 1.
   *
   * @param varName the name of the variable we are modifying.
   */
  public void decr(String varName) {
    variables.replace(varName, variables.get(varName) - 1);
  }

  /**
   * Checks whether the variable is zero.
   *
   * @param varName the variable we are checking.
   * @return true if the variable is zero, else return false.
   */
  public boolean isZero(String varName) {
    return (variables.get(varName) == 0);
  }

  public int getValue(String varName) {
    return (variables.get(varName));
  }

  /**
   * A simple method to allow the main instruction handler to print the value of a variable
   * to the console.
   *
   * @param varName the name of the variable to be printed.
   * @param console the console object we want to use to print the value.
   * @param line    the instruction that we are calling this method from.
   */
  public void printValue(String varName, Console console, int line) {
    console.printVariable(varName, variables.get(varName), line);
  }
}
