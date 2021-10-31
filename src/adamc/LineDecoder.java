package adamc;

import adamc.conditions.*;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class LineDecoder {
  private final String line;
  private final HashMap<Integer, Operator> operatorIndex;
  private HashMap<String, Condition> conditionMap;
  private final VarHandler vHandler;

  public LineDecoder(String line, VarHandler vHandler) {
    this.line = line.replaceAll("\\s", "");
    operatorIndex = getOperatorIndex();
    this.vHandler = vHandler;

    // Handle everything inside brackets first
    //TODO: Once a condition is created, replace that part of the string with a UUID thingy
    //TODO: Sort out the bounds for conditions, generating and looping through certain bounds
    //TODO: Loop until all conditions are created
    //TODO: Store the final result in some list somewhere, so we can just call methods on the array
  }

  private Condition getCondition(Pair<Integer, Integer> bounds) {
    // Order of precedence:
    // MATHEMATICAL FUNCTIONS >>> LOGICAL OPERATIONS >>> ASSIGNMENT
    // MATHEMATICAL FUNCTIONS:
    // MULTIPLY / DIVIDE >>> ADDITION / SUBTRACTION
    // LOGICAL FUNCTIONS:
    // NOT >>> AND >>> OR >>> EQUAL, NEQUAL, GREATER, LESSER, GEQUAL, LEQUAL
    // ASSIGNMENT:
    // ASSIGN


    // Loop through the hashmap
    // We shall start with the mathematical functions
    // Multiply and Divide

    boolean hasFurtherOperator = false;

    for (Map.Entry<Integer, Operator> opSet : operatorIndex.entrySet()) {
      if (opSet.getKey() < bounds.getKey() || opSet.getKey() > bounds.getValue()) {
        continue;
      }

      hasFurtherOperator = true;

      if (opSet.getValue().equals(Operator.PRODUCT)) {
        Pair<Integer,Integer> newBounds = getBounds(opSet.getKey(), bounds);
        return new Product(getCondition(new Pair<Integer, Integer>(newBounds.getKey(), opSet.getKey())), getCondition(new Pair<Integer, Integer>(newBounds.getKey(), opSet.getKey())));
      } else if (opSet.getValue().equals(Operator.DIVIDE)){
        Pair<Integer,Integer> newBounds = getBounds(opSet.getKey(), bounds);
        return new Division(getCondition(new Pair<Integer, Integer>(newBounds.getKey(), opSet.getKey())), getCondition(new Pair<Integer, Integer>(newBounds.getKey(), opSet.getKey())));
      }
    }

    for (Map.Entry<Integer, Operator> opSet : operatorIndex.entrySet()) {
      if (opSet.getKey() < bounds.getKey() || opSet.getKey() > bounds.getValue()) {
        continue;
      }

      hasFurtherOperator = true;

      if (opSet.getValue().equals(Operator.SUM)) {
        Pair<Integer,Integer> newBounds = getBounds(opSet.getKey(), bounds);
        return new Sum(getCondition(new Pair<Integer, Integer>(newBounds.getKey(), opSet.getKey())), getCondition(new Pair<Integer, Integer>(newBounds.getKey(), opSet.getKey())));
      } else if (opSet.getValue().equals(Operator.MINUS)){
        Pair<Integer,Integer> newBounds = getBounds(opSet.getKey(), bounds);
        return new Minus(getCondition(new Pair<Integer, Integer>(newBounds.getKey(), opSet.getKey())), getCondition(new Pair<Integer, Integer>(newBounds.getKey(), opSet.getKey())));
      }
    }

    if (!hasFurtherOperator) {
      if (this.vHandler.hasVariable(line.substring(bounds.getKey(), bounds.getValue()))) {
        return new Variable(this.vHandler.getValue(line.substring(bounds.getKey(), bounds.getValue())));
      } else {
        this.vHandler.createVariable(line.substring(bounds.getKey(), bounds.getValue()));
        return new Variable(this.vHandler.getValue(line.substring(bounds.getKey(), bounds.getValue())));
      }

    }

    return null;

  }

  private Pair<Integer, Integer> getBounds(int opIndex, Pair<Integer, Integer> oldBounds) {
    int before = oldBounds.getKey(), after = oldBounds.getValue();
    // Figure out the before first
    for (int i = opIndex - 1; i > 0; --i) {
      //TODO: Handle brackets
      if ("+*-/=><&|!".indexOf(line.charAt(i)) != -1) {
        // If we have found the index then we want to return the index +1
        before = i + 1;
        break;
      }
    }

    // Then handle the after (use a +2 to index for the ==, etc.
    for (int i = opIndex + 2; i < line.length(); i++) {
      //TODO: Handle brackets
      if ("+*-/=><&|!".indexOf(line.charAt(i)) != -1) {
        // If we have found the index then we want to return the index - 1
        before = i - 1;
        break;
      }
    }

    return new Pair<>(before, after);
  }

  private HashMap<Integer, Operator> getOperatorIndex() {
    HashMap<Integer, Operator> instructionMap = new HashMap<>();
    for (int i = 0; i < line.length(); i++) {
      switch (line.charAt(i)) {
        case '+' -> instructionMap.put(i, Operator.SUM);
        case '*' -> instructionMap.put(i, Operator.PRODUCT);
        case '-' -> instructionMap.put(i, Operator.MINUS);
        case '/' -> instructionMap.put(i, Operator.DIVIDE);
        case '=' -> {
          if (i == line.length() - 1) {
            instructionMap.put(i, Operator.ASSIGN);
          } else {
            if (line.charAt(i + 1) == '=') {
              instructionMap.put(i, Operator.EQUAL);
              i++;
            } else {
              instructionMap.put(i, Operator.ASSIGN);
            }
          }

        }
        case '>' -> {
          if (i == line.length() - 1) {
            instructionMap.put(i, Operator.GREATER);
          } else {
            if (line.charAt(i + 1) == '=') {
              instructionMap.put(i, Operator.GEQUAL);
              i++;
            } else {
              instructionMap.put(i, Operator.GREATER);
            }
          }
        }
        case '<' -> {
          if (i == line.length() - 1) {
            instructionMap.put(i, Operator.LESSER);
          } else {
            if (line.charAt(i + 1) == '=') {
              instructionMap.put(i, Operator.LEQUAL);
              i++;
            } else {
              instructionMap.put(i, Operator.LESSER);
            }
          }
        }
      }
    }
    return instructionMap;
  }
}
