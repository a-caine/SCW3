package adamc.conditions;
/**
 * Abstract class that I will be using in order to allow the interpreter to parse
 * difficult conditional statements.
 * 
 * @author Adam Caine
 */
public abstract class Condition {



    protected final Condition before, after;

    /**
     * Constructor for the condition, every condition needs to have two further conditions
     * along with an operator.
     * 
     * @param before the condition that appears before the operator
     * @param after the condition that appears after the operator
     */
    public Condition(Condition before, Condition after) {
        this.before = before;
        this.after = after;
    }

    /**
     * Method to be implemented by specific condition.
     * 
     * @return the condition resulting from the condition's specific behaviour
     */
    public abstract Condition getResult();




}