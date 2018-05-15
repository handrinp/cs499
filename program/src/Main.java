import java.awt.Color;
import java.io.File;

public class Main {
  public static void main(String[] args) {
    VimVideo vid = new VimVideo(new File(args[0]));

    if (args.length == 1) {
      vid.play();
    } else if (args.length == 2) {
      vid.createGIF(args[1], 1000);
    } else if (args.length == 5) {
      vid.createGIF(args[1], 1000, parseColor(args[2]), parseColor(args[3]), parseColor(args[4]));
    } else if (args.length == 7) {
      vid.createGIF(args[1], 1000, parseColor(args[2]), parseColor(args[3]), parseColor(args[4]), parseColor(args[5]),
          parseColor(args[6]));
    } else {
      System.out.println("Invalid usage - see the readme.");
    }
  }

  private static Color parseColor(String hex) {
    int r = Integer.parseInt(hex.substring(1, 3), 16);
    int g = Integer.parseInt(hex.substring(3, 5), 16);
    int b = Integer.parseInt(hex.substring(5, 7), 16);
    return new Color(r, g, b);
  }
}
