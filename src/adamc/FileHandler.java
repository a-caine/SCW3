package adamc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * A class to take a file from a path and convert that to a list of string objects so that we can
 * parse the code from said file.
 */
public class FileHandler {

  private final String[] lines;

  /**
   * This method takes a path, reads the text from that path (if it exists), before splitting
   * that text into lines that we can then pass onto different parts of the program in order
   * to run the interpreter.
   *
   * @param filePath the path of the file we want to read the data from.
   */
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


    lines = fileData.toString().split(";");
  }


  /**
   * Returns a specific line (instruction) from the array of lines with the specified line num.
   *
   * @param lineNum the line number that we will return.
   * @return the line or instruction at the line number specified.
   */
  public String getLine(int lineNum) {
    return lines[lineNum];
  }

  /**
   * Returns the total number of lines in the code.
   *
   * @return an integer equal to the number of lines of code.
   */
  public int getTotalLines() {
    return lines.length;
  }
}
