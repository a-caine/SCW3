package adamc;

public class Main {

  public static void main(String[] args) {
    fileHandler handler = new fileHandler("programs/example.txt");
    InstructionHandler iHandler = new InstructionHandler();
    iHandler.start(handler);
  }
}
