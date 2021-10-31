package adamc.conditions;

public class Product extends Condition{
  /**
   * Constructor for the condition, every condition needs to have two further conditions
   * along with an operator.
   *
   * @param before the condition that appears before the operator
   * @param after  the condition that appears after the operator
   */
  public Product(Condition before, Condition after) {
    super(before, after);
  }

  @Override
  public Condition getResult() {
    Variable varBefore, varAfter;
    varBefore = (Variable)before.getResult();
    varAfter = (Variable)after.getResult();
    return new Variable(varBefore.getValue() * varAfter.getValue());
  }
}
