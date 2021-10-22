package adamc;

import java.util.Vector;

/**
 * This is the main class for the program, it works with a rather crap implementation of a
 * fetch, decode, execute loop, except instead of working with binary, it works with a bunch
 * of string objects.
 */
public class InstructionHandler {

  private int instruction;
  private boolean programRunning = true;
  private boolean programFailed = false;

  private VarHandler varHandler;
  private final Vector<Integer> loopInstructions = new Vector<>();
  private final Vector<String> loopVariables = new Vector<>();

  private final Console console = new Console();

  /**
   * This method starts the main program loop, which constantly runs a fetch decode execute cycle.
   * It keeps track of what instruction we are currently on, and uses that to fetch the next
   * instruction.
   * It also keeps track of the final instruction in order to exit the program before we start
   * asking for data that isn't there.
   * @param handler an instance of a FileHandler object so that we can request the raw lines of
   *                code as well as the total number of instructions.
   */
  public void start(StringHandler handler) {
    programRunning = true;
    programFailed = false;
    instruction = 0;
    Instruction currentInstruction;
    varHandler = new VarHandler();

    int finalInstruction = handler.getTotalLines();
    int totalInstructions = 0;

    String data;


    while (programRunning) {
      console.printPriority("***** INSTRUCTION " + instruction + " *****");

      // Fetch instruction from fileHandler
      data = handler.getLine(instruction);

      // Send to interpreter to decode
      currentInstruction = decodeInstruction(data);

      // Output what operator we are using and what operand it is working on for user readability
      console.printString("Operator: " + currentInstruction.getOperator() + ", Operand: " + currentInstruction.getOperand());

      // Send decoded instruction to variable manager (or loop if loop case)
      executeInstruction(currentInstruction);

      instruction++;
      totalInstructions++;

      if (instruction + 1 > finalInstruction) {
        programRunning = false;
      }
    }
    if (!programFailed) {
      console.printSuccess("Program finished, total lines of code: " + finalInstruction + ", total instructions executed: " + totalInstructions);
    }

  }

  /**
   * Super simple method to set the instruction variable to an integer of the new instruction to
   * jump to.
   * @param instruction the instruction (or line) that we want to jump to.
   */
  public void setInstruction(int instruction) {
    this.instruction = instruction;
  }

  /**
   * Takes the raw string of a line of code and decodes it into an instruction (operator and operand).
   * @param inst The raw line of code (instruction) that we want to decode.
   * @return an Instruction formed of an operator and operand.
   */
  private Instruction decodeInstruction(String inst) {
    // Strip white space from lead of instruction
    boolean foundStart = false;
    int startIndex = 0;
    while (!foundStart) {
      if (inst.charAt(startIndex) == ' ') {
        startIndex++;
      } else {
        foundStart = true;
      }
    }

    inst = inst.substring(startIndex);

    // Split the rest of the line into an operand and operator.
    String operator = null;
    String operand = null;
    String[] parsedInst = inst.split(" ");
    if (parsedInst.length > 0) {
      operator = parsedInst[0];
    }

    // we can assume that the operand (or variable) will be second
    if (parsedInst.length > 1) {
      operand = parsedInst[1];
    }

    return new Instruction(operator, operand);
  }

  /**
   * Takes the basic Instruction formed of an operator and operand and calls the correct methods
   * relating to that instruction accordingly.
   * @param inst the instruction to be executed.
   */
  private void executeInstruction(Instruction inst) {

    if (inst.getOperator() == null) {
      console.printError("No operator found", instruction);
      programRunning = false;
      programFailed = true;
      return;
    }
    // Special Instruction
    if (inst.getOperator().equals("end")) {
      // If we satisfy the while condition, simply loop, else:
      if (!varHandler.isZero(loopVariables.get(loopVariables.size() - 1))) {

        setInstruction(loopInstructions.get(loopInstructions.size() - 1));
      } else {
        // Get rid of our loop instruction and variables
        loopInstructions.remove(loopInstructions.size() - 1);
        loopVariables.remove(loopVariables.size() - 1);
      }

      // Break out of the function before we run into more issues
      return;

    }

    // Everything else deals with a variable name which we need to check for

    // Figure out variable
    if (inst.getOperand() == null) {
      console.printError("Expected variable name, found nothing", instruction);
      programRunning = false;
      programFailed = true;
      return;
    }
    if (!varHandler.hasVariable(inst.getOperand())) {
      // If we do not have a variable then we need to create one
      varHandler.createVariable(inst.getOperand());
    }

    // Once we have the variable, we can execute the instruction
    switch (inst.getOperator()) { // Simple enhanced switch function (Requires Java 13)
      case "incr" -> varHandler.incr(inst.getOperand());
      case "decr" -> varHandler.decr(inst.getOperand());
      case "clear" -> varHandler.clear(inst.getOperand());
      case "while" -> {
        loopVariables.add(inst.getOperand());
        loopInstructions.add(instruction);
      }
      default -> {
        console.printError("No instruction found", instruction);
        programRunning = false;
        programFailed = true;
        return;
      }
    }

    // Prints the value of our variable to the console
    varHandler.printValue(inst.getOperand(), this.console, instruction);

  }
}
