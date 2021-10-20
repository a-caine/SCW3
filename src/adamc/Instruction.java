package adamc;

public class Instruction {

  private String operator, operand;

  public Instruction(String operator, String operand) {
    this.operand = operand;
    this.operator = operator;
  }

  public String getOperator() {
    return this.operator;
  }

  public String getOperand() {
    return this.operand;
  }
}
