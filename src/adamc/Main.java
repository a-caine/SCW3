package adamc;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main class of our program, where the program starts.
 */
public class Main extends Application {

  private static ListView<Text> console;


  /**
   * The main function, where our program starts, asks the user for a path to the file we want
   * to run, and executes on that file.
   *
   * @param args The arguments that the user can pass through to our program from the command line.
   */
  public static void main(String[] args) {

    launch(args);
  }

  /**
   * Our entry point for our JavaFX GUI. We create all of our GUI elements, and define the functions
   * that will run when certain actions are called upon from the user.
   *
   * @param stage the top level container of our program, this parameter is automatically created and
   *              passed by the platform, as it is our primary stage.
   */
  @Override
  public void start(Stage stage) {
    // Create our main VBOX and a small HBOX for our buttons
    VBox vbox = new VBox();

    HBox buttonBox = new HBox();
    HBox.setHgrow(buttonBox, Priority.ALWAYS);

    // Create a Grid Pane
    GridPane gp = new GridPane();

    gp.setHgap(10); // Horizontal Gap
    gp.setVgap(4); // Vertical Gap
    gp.setPadding(new Insets(10)); // Padding

    VBox.setVgrow(gp, Priority.ALWAYS); // Keeps the form of our window consistent

    // Create our buttons
    Button loadFromFile = new Button("Load From File");
    Button executeCode = new Button("Execute Code");

    // Create our labels
    Label editorLabel = new Label("Editor");
    Label consoleLabel = new Label("Console");

    // Create our text area for editing the code
    TextArea editor = new TextArea();
    // Create our console, which will use a list view
    console = new ListView<>();

    editor.setMinHeight(600);
    console.setEditable(false);
    console.setMinWidth(450);

    // Takes the action created from our button and, once called, will run some code to bring
    // up a file dialogue, defaulting to the programs directory, and once the user has selected
    // a file, read the contents of that file into the editor
    loadFromFile.setOnAction(actionEvent -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setInitialDirectory(new File("./programs"));
      File file = fileChooser.showOpenDialog(stage);
      if (file != null) {
        editor.setText(getStringFromFile(file));
      }
    });

    // Takes the action created from our executeCode button and will create a new interpreter
    // and execute the code found inside of the editor text area
    executeCode.setOnAction(actionEvent -> {
      StringHandler sHandler = new StringHandler(editor.getText());
      InstructionHandler iHandler = new InstructionHandler();
      iHandler.start(sHandler);
    });

    TextField consoleInput = new TextField();

    // Once the user presses enter inside of the console TextField, we run the sendCommand
    // method, passing through the text the user entered, before clearing the text entered
    consoleInput.setOnKeyReleased(keyEvent -> {
      if (keyEvent.getCode().equals(KeyCode.ENTER)) {
        sendCommand(consoleInput.getText());
        consoleInput.setText("");
      }
    });

    // Add our buttons to the HBOX buttonBox
    buttonBox.getChildren().add(loadFromFile);
    buttonBox.getChildren().add(executeCode);

    // Add our elements to the grid pane
    gp.add(editorLabel, 0, 0);
    gp.add(editor, 0, 1);
    gp.add(buttonBox, 0, 2);
    gp.add(consoleLabel, 1, 0);
    gp.add(console, 1, 1);
    gp.add(consoleInput, 1, 2);

    // Set our stage to a scene formed only of our grid pane
    stage.setScene(new Scene(gp));
    // Open the window
    stage.show();
  }

  /**
   * Adds a text object to our private ListView, also ensures that the console stores no more
   * than 256 lines
   *
   * @param text the Text object that we want to add to the ListView
   */
  public static void addToConsole(Text text) {
    if (console.getItems().size() > 256) {
      console.getItems().remove(0);
    }

    console.getItems().add(text);
    int index = console.getItems().size();
    console.scrollTo(index);
  }

  /**
   * Takes a File object and reads through that file, building a string using a StringBuilder
   * object before returning the string.
   *
   * @param file the File object that we want to extract the data from.
   * @return returns a String of all the data inside the file specified.
   */
  public String getStringFromFile(File file) {
    StringBuilder fileData = new StringBuilder();
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        fileData.append(scanner.nextLine()).append('\n');
      }

      System.out.println(fileData);
    } catch (FileNotFoundException ex) {

      ex.printStackTrace();
    }

    return fileData.toString();
  }

  /**
   * Handle's the way commands are sent to the ListView object. May be updated to call external
   * functions.
   *
   * @param command the string the user entered as their command
   */
  public void sendCommand(String command) {
    Text commandText = new Text(">>> " + command);
    // Formatting
    commandText.setStroke(Color.BLACK);
    commandText.setStrokeWidth(1);

    addToConsole(commandText);
    switch (command) {
      case "clear" -> console.getItems().clear();
      case "help" -> {
        Text helpText = new Text("Commands");
        // Formatting
        helpText.setStroke(Color.BLACK);
        helpText.setStrokeWidth(1);
        helpText.setUnderline(true);

        addToConsole(helpText);
        addToConsole(new Text("clear - clears the console"));
        addToConsole(new Text("help - brings up this message"));
      }
      default -> {
        Text errorText = new Text("Unknown command \"" + command + "\". Type help for a list of commands");
        // Formatting
        errorText.setStroke(Color.RED);
        errorText.setStrokeWidth(1);

        addToConsole(errorText);
      }
    }

  }
}
