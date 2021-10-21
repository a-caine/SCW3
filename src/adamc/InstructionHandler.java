package adamc;

import java.util.Vector;

public class InstructionHandler {

  private int instruction, finalInstruction;

  private VarHandler varHandler;
  private String currentVar;
  private Vector<Integer> loopInstructions = new Vector<>();
  private Vector<String> loopVariables = new Vector<>();

  private Console console = new Console();

  private int currentInstruction;

  public void start(FileHandler handler) {
    boolean programRunning = true;
    instruction = 0;
    Instruction currentInstruction;
    varHandler = new VarHandler();

    finalInstruction = handler.getTotalLines();

    String data;


    while (programRunning) {
      System.out.println("***** INSTRUCTION " + instruction + " *****");
      // main interpreter loop

      // Fetch instruction from fileHandler
      data = handler.getLine(instruction);


      // Send to interpreter to decode
      currentInstruction = decodeInstruction(data);

      System.out.println("Operator: " + currentInstruction.getOperator() + ", Operand: " + currentInstruction.getOperand());


      // Send decoded instruction to variable manager (or loop if loop case)
      executeInstruction(currentInstruction);

      instruction++;

      if (instruction + 1 > finalInstruction) {
        programRunning = false;
      }
    }
  }

  public void setInstruction(int instruction) {
    this.instruction = instruction;
  }

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

    String operator = null;
    String operand = null;
    String[] parsedInst = inst.split(" ");
    if (parsedInst.length > 0) {
      operator = parsedInst[0];
    }

    // we can generally assume that the operand (or variable) will be second
    if (parsedInst.length > 1) {
      operand = parsedInst[1];
    }



    return new Instruction(operator, operand);
  }

  private void executeInstruction(Instruction inst) {

    if (inst.getOperator() == null) {
      console.printError("No operand found", instruction);
    }
    // Special Instruction
    if (inst.getOperator().equals("end")) {
      // If we satisfy the while condition, simply loop, else:
      if (!varHandler.isZero(loopVariables.get(loopVariables.size() - 1))) {

        setInstruction(loopInstructions.get(loopInstructions.size() - 1));
      } else {
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
    }
    if (!varHandler.hasVariable(inst.getOperand())) {
      // If we do not have a variable then we need to create one
      varHandler.createVariable(inst.getOperand());
    }

    // Once we have the variable, we can execute the instruction
    switch (inst.getOperator()) {
      case "incr" -> varHandler.incr(inst.getOperand());
      case "decr" -> varHandler.decr(inst.getOperand());
      case "clear" -> varHandler.clear(inst.getOperand());
      case "while" -> {
        loopVariables.add(inst.getOperand());
        loopInstructions.add(instruction);
      }
      default -> console.printError("No instruction found", instruction);
    }

    varHandler.printValue(inst.getOperand(), this.console, instruction);

  }
}
