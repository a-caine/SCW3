package adamc;

public class StringHandler {

  private final String[] lines;

  public StringHandler(String data) {
    data = data.replace("\n", "");
    lines = data.split(";");
  }


  public String getLine(int lineNum) {
    return lines[lineNum];
  }

  public int getTotalLines() {
    return lines.length;
  }
}
