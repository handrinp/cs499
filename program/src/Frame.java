public class Frame {
  private long timestamp;
  private long delta;
  private int cursorX;
  private int cursorY;
  private String[] lines;

  public Frame(long timestamp, long delta, int cursorX, int cursorY, String[] lines) {
    this.timestamp = timestamp;
    this.delta = delta;
    this.cursorX = cursorX;
    this.cursorY = cursorY;
    this.lines = new String[lines.length];
    System.arraycopy(lines, 0, this.lines, 0, lines.length);
  }

  public long getTimestamp() {
    return timestamp;
  }

  public long getDelta() {
    return delta;
  }

  public int getWidth() {
    int maxWidth = -1;

    for (String line : lines) {
      maxWidth = Math.max(maxWidth, line.length());
    }

    return maxWidth;
  }

  public int getHeight() {
    return lines.length;
  }

  public int getX() {
    return cursorX;
  }

  public int getY() {
    return cursorY;
  }

  public String getLines() {
    StringBuilder sb = new StringBuilder();

    for (String line : lines) {
      sb.append(line);
      sb.append(System.lineSeparator());
    }

    return sb.toString();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(lines.length);
    sb.append('~');
    sb.append(timestamp);
    sb.append('~');
    sb.append(cursorX);
    sb.append('~');
    sb.append(cursorY);
    sb.append(System.lineSeparator());
    sb.append(getLines());
    return sb.toString();
  }
}
