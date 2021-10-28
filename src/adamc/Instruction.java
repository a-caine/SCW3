package adamc;

/**
 * A simple container class to allow us to easily pass instructions back as the result of a method.
 */
public class Instruction {

  private final Condition condition;
  private final Operator operator;

  /**
   * The constructor takes both the operator and operand and stores them privately.
   *
   * @param operator the operator code of the instruction.
   * @param operand  the variable that we are performing the instruction on.
   */
  public Instruction(Condition conditon, Operator operator) {
    this.condition = conditon;
    this.operator = operator;
  }

  /**
   * Calls the getResult function on the condition stored in the instruction
   */
  public void exectue() {
    this.condition.getResult();
  }
  
}
