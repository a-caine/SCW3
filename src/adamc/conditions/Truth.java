package adamc.conditions;

public class Truth extends Condition{

  private boolean truth;

  /**
   * Constructor for the condition, every condition needs to have two further conditions
   * along with an operator.
   *
   * @param before the condition that appears before the operator
   * @param after  the condition that appears after the operator
   */
  public Truth(boolean truth) {
    super(null, null);

    this.truth = truth;
  }

  @Override
  public Condition getResult() {
    return this;
  }

  public boolean getTruth() {
    return this.truth;
  }

  public void setTruth(boolean truth) {
    this.truth = truth;
  }
}
