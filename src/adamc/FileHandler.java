package adamc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class FileHandler {

  private final String[] lines;

  public FileHandler(String filePath) {
    StringBuilder fileData = new StringBuilder();
    try {

      File file = new File(filePath);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        fileData.append(scanner.nextLine());
      }

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
