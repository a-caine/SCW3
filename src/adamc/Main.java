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
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

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
    gp.setPadding( new Insets(10) ); // Padding

    //gp.setGridLinesVisible(true);

    VBox.setVgrow(gp, Priority.ALWAYS); // Keeps the form consistent

    // Create our buttons
    Button loadFromFile = new Button("Load From File");
    Button executeCode = new Button("Execute Code");



    // Create our labels
    Label editorLabel = new Label("Editor");
    Label consoleLabel = new Label("Console");

    // Create our text area
    TextArea editor = new TextArea();
    //Create our console, which will use a list view
    console = new ListView<>();

    editor.setMinHeight(600);
    console.setEditable(false);
    console.setMinWidth(450);

    loadFromFile.setOnAction(actionEvent -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setInitialDirectory(new File("./programs"));
      File file = fileChooser.showOpenDialog(stage);
      if (file != null) {
        editor.setText(getStringFromFile(file));
      }
    });

    executeCode.setOnAction(actionEvent -> {
      StringHandler sHandler = new StringHandler(editor.getText());
      InstructionHandler iHandler = new InstructionHandler();
      iHandler.start(sHandler);
    });

    TextField consoleInput = new TextField();

    buttonBox.getChildren().add(loadFromFile);
    buttonBox.getChildren().add(executeCode);

    gp.add(editorLabel, 0,0);
    gp.add(editor, 0, 1);
    gp.add(buttonBox, 0, 2);
    gp.add(consoleLabel, 1, 0);
    gp.add(console, 1, 1);
    gp.add(consoleInput, 1, 2);

    stage.setScene(new Scene(gp));
    stage.show();
  }

  public static void addToConsole(Text text) {
    if (console.getItems().size() > 256) {
      console.getItems().remove(0);
    }

    console.getItems().add(text);
    int index = console.getItems().size();
    console.scrollTo(index);
  }

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
}
