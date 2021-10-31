package adamc.conditions;

public class Variable extends Condition{

  private int value;

  /**
   * Constructor for the condition, every condition needs to have two further conditions
   * along with an operator.
   *
   * @param before the condition that appears before the operator
   * @param after  the condition that appears after the operator
   */
  public Variable(int value) {
    super(null, null);

    this.value = value;
  }

  @Override
  public Condition getResult() {
    return this;
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int value) {
    this.value = value;
  }


}
