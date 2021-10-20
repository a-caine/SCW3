package adamc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class fileHandler {

  private final String[] lines;

  public fileHandler(String filePath) {
    StringBuilder fileData = null;
    try {

      File file = new File(filePath);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        fileData.append(scanner.nextLine());

      }

      fileData = new StringBuilder(fileData.substring(4));

      System.out.println(fileData);
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }

    Vector<String> tempLines = new Vector<>();

    lines = fileData.toString().split(";");
  }

  public String getLine(int lineNum) {
    return lines[lineNum];
  }

  public int getTotalLines() {
    return lines.length;
  }
}
