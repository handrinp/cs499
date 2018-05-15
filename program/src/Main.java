import java.awt.Color;
import java.io.File;

public class Main {
  public static void main(String[] args) {
    new VimVideo(args[0]).createGIF(System.getProperty("user.home") + "/Desktop/vid.gif", 1000, Color.DARK_GRAY,
        Color.WHITE, Color.GRAY);
  }
}
