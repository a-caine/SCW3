package adamc;

/**
 * A simple container class to allow us to easily pass instructions back as the result of a method.
 */
public class Instruction {

  private final String operator, operand;

  /**
   * The constructor takes both the operator and operand and stores them privately.
   *
   * @param operator the operator code of the instruction.
   * @param operand  the variable that we are performing the instruction on.
   */
  public Instruction(String operator, String operand) {
    this.operand = operand;
    this.operator = operator;
  }

  /**
   * A getter for the operator String.
   *
   * @return the operator String.
   */
  public String getOperator() {
    return this.operator;
  }

  /**
   * A getter for the operand String.
   *
   * @return the operand String.
   */
  public String getOperand() {
    return this.operand;
  }
}
