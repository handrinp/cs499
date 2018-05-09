public class Frame {
  private long timestamp;
  private long delta;
  private String[] lines;

  public Frame(long timestamp, long delta, String[] lines) {
    this.timestamp = timestamp;
    this.delta = delta;
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
    sb.append(System.lineSeparator());
    sb.append(getLines());
    return sb.toString();
  }

  // ayyy
}
