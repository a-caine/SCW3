package adamc;


import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * A simple class that allows our program to communicate with the terminal and print errors and
 * values of variables to it.
 * This class may be updated if I add a GUI to the interpreter in the future.
 */
public class Console {

  /**

   * Prints an error to the console.

   *
   * @param errorMessage the message to be printed.
   * @param line         the instruction that the error occurred on.
   */
  public void printError(String errorMessage, int line) {
    Text errorText = new Text();
    errorText.setText("Error at line " + line + "\n" + errorMessage);
    errorText.setStrokeWidth(1);
    errorText.setStroke(Color.RED);

    Main.addToConsole(errorText);
  }

  /**
   * Prints a variables value to the console.
   *
   * @param varName the name of the variable to be printed.
   * @param value   the value of the variable to be printed.
   * @param line    the instruction that this value was printed on.
   */
  public void printVariable(String varName, int value, int line) {
    Main.addToConsole(new Text("Value of " + varName + " = " + value + ", Instruction line " + line));
  }

  /**
   * Prints a simple string to the console.
   *
   * @param string the string to be printed to the console.
   */
  public void printString(String string) {
    Main.addToConsole(new Text(string));
  }

  /**
   * Prints a message highlighted in green text to indicate success.
   *
   * @param successMessage the message we are sending to the console.
   */
  public void printSuccess(String successMessage) {
    Text successText = new Text();
    successText.setText(successMessage);
    successText.setStrokeWidth(1);
    successText.setStroke(Color.GREEN);

    Main.addToConsole(successText);
  }

  /**
   * Prints a message highlighted in order to make it stand out from other messages to the console.
   *
   * @param message the message to be sent to the console.
   */
  public void printPriority(String message) {
    Text text = new Text();
    text.setText(message);
    text.setFill(Color.WHITE);
    text.setStrokeWidth(1);
    text.setStroke(Color.BLACK);

    Main.addToConsole(text);
  }

}
