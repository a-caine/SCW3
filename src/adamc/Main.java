package adamc;

public class Main {

  public static void main(String[] args) {
    FileHandler handler = new FileHandler("programs/example.txt");
    InstructionHandler iHandler = new InstructionHandler();
    iHandler.start(handler);
  }
}
