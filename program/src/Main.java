public class Main {
  public static void main(String[] args) {
    VimVideo vid = new VimVideo("/tmp/cs499/program/foo");

    for (int i = 0; i < vid.numFrames(); ++i) {
      System.out.println("Printing frame " + i);
      System.out.println(vid.getFrame(i));
    }
  }
}
