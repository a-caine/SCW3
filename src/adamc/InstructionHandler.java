package adamc;

public class InstructionHandler {

  private int instruction, finalInstruction;

  private int currentInstruction;

  public void start(fileHandler handler) {
    boolean programRunning = true;
    instruction = 0;
    Instruction currentInstruction;

    finalInstruction = handler.getTotalLines();

    String data;


    while (programRunning) {
      // main interpreter loop

      // Fetch instruction from fileHandler
      data = handler.getLine(instruction);


      // Send to interpreter to decode
      currentInstruction = decodeInstruction(data);


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
    System.out.println(inst);
    // We are going to get data in the following format:
    // incr X
    // clear X
    // decr X
    // while X
    // end
    // From this, no matter what the data is, if we call string.split with space as our regex
    // we will get our operator
    String operator, operand;
    String[] parsedInst = inst.split(" ");
    operator = parsedInst[0];
    // we can generally assume that the operand (or variable) will be second
    operand = parsedInst[1];
    return new Instruction(operator, operand);
  }

  private void executeInstruction(Instruction inst) {
    // Special Instruction
    if (inst.getOperator().equals("end")) {

    }

    // Everything else deals with a variable name which we need to check for


  }
}
