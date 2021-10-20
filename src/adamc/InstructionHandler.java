package adamc;

public class InstructionHandler {

    private boolean programRunning;
    private int instruction, finalInstruction;

    private int currentInstruction;

    public void start(fileHandler handler) {
        programRunning = true;
        instruction = 0;

        finalInstruction = handler.getTotalLines();

        String data;


        while (programRunning) {
            // main interpreter loop

            // Fetch instruction from fileHandler
            data = handler.getLine(instruction);



            // Send to interpreter to decode
            decodeInstruction(data);


            // Send decoded instruction to variable manager (or loop if loop case)
            instruction++;
            if (instruction + 1 > finalInstruction) {
                programRunning = false;
            }
        }
    }

    private Instruction decodeInstruction(String inst) {
        System.out.println(inst);
        // We are going to get data in the following format:
        // incr X
        // clear X
        // decr X
        // while X
        // end
        return new Instruction("p", "p");
    }
}
