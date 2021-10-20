package adamc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileHandler {

  private String fileData;


  public fileHandler(String filePath) {
    try {
      File file = new File(filePath);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        fileData += scanner.nextLine() + "\n";
      }

      System.out.println(fileData);
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
  }
}
