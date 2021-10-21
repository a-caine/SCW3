package adamc;

import java.util.HashMap;

public class VarHandler {
  private final HashMap<String, Integer> variables = new HashMap<>();

  public boolean hasVariable(String varName) {
    return variables.containsKey(varName);
  }

  public void createVariable(String varName) {
    variables.put(varName, 0);
  }

  public void clear(String varName) {
    variables.replace(varName, 0);
  }

  public void incr(String varName) {
    variables.replace(varName, variables.get(varName) + 1);
  }

  public void decr(String varName) {
    variables.replace(varName, variables.get(varName) - 1);
  }

  public boolean isZero(String varName) {
    return (variables.get(varName) == 0);
  }

  public void printValue(String varName, Console console, int line) {
    console.printVariable(varName, variables.get(varName), line);
  }
}
