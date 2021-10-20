package adamc;

import java.util.HashMap;

public class varHandler {
  private HashMap<String, Integer> variables = new HashMap<>();

  public boolean hasVariable(String varName) {
    return variables.containsKey(varName);
  }

  public void createVariable(String varName) {
    variables.put(varName, 0);
  }

  public void clear(String varName) {
    variables.replace(varName, 0);
  }
}
