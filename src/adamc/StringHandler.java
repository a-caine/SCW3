package adamc;

/**
 * A simple class for taking a string and stripping away newlines, and splitting it instead by
 * the semicolon (;) character.
 */
public class StringHandler {

  private final String[] lines;

  /**
   * Takes an input of a string, removes all newlines, then splits it by the semicolon (;)
   * character.
   *
   * @param data the string to be processed.
   */
  public StringHandler(String data) {
    data = data.replace("\n", "");
    lines = data.split(";");
  }

  /**
   * Grab a specific string from our private list of String objects.
   *
   * @param lineNum the index of the string to grab.
   * @return the string associated with the index passed into the method.
   */
  public String getLine(int lineNum) {
    return lines[lineNum];
  }

  /**
   * Prints the total number of String objects in our String array.
   *
   * @return the size of the array storing all of our String objects.
   */
  public int getTotalLines() {
    return lines.length;
  }
}
