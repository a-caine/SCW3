package adamc;

import java.util.Scanner;

/**
 * The main class of our program, where the program starts.
 */
public class Main {

  /**
   * The main function, where our program starts, asks the user for a path to the file we want
   * to run, and executes on that file.
   *
   * @param args The arguments that the user can pass through to our program from the command line.
   */
  public static void main(String[] args) {
    System.out.println("Please enter the path of the program you would like to run" +
        " (relative to the programs folder of this program");
    Scanner scanner = new Scanner(System.in);
    String path = scanner.nextLine();
    FileHandler handler = new FileHandler("programs/" + path);
    InstructionHandler iHandler = new InstructionHandler();
    iHandler.start(handler);
  }
}
