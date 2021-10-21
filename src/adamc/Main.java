package adamc;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * The main class of our program, where the program starts.
 */
public class Main extends Application {

  /**
   * The main function, where our program starts, asks the user for a path to the file we want
   * to run, and executes on that file.
   *
   * @param args The arguments that the user can pass through to our program from the command line.
   */
  public static void main(String[] args) {
    launch(args);
    System.out.println("Please enter the path of the program you would like to run" +
        " (relative to the programs folder of this program");
    Scanner scanner = new Scanner(System.in);
    String path = scanner.nextLine();
    FileHandler handler = new FileHandler("programs/" + path);
    InstructionHandler iHandler = new InstructionHandler();
    iHandler.start(handler);
  }

  @Override
  public void start(Stage stage) throws Exception {
    stage.setScene(new Scene(createContent(), 300, 300));
    stage.show();
  }

  private Parent createContent() {
    return new StackPane(new Text("Hello World"));
  }
}
