import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class VimVideo {
  private static final int CHAR_WIDTH = 12;
  private static final int CHAR_HEIGHT = 20;
  private List<Frame> frames;

  public VimVideo(String fileName) {
    File inFile = new File(fileName + ".rec");
    frames = new ArrayList<>();

    try (Scanner in = new Scanner(inFile)) {
      while (in.hasNextLine()) {
        final String[] frameData = in.nextLine().split("~");
        int numLines = Integer.parseInt(frameData[0]);
        String[] lines = new String[numLines];

        for (int i = 0; i < numLines; ++i) {
          lines[i] = in.nextLine();
        }

        long timestamp = Long.parseLong(frameData[1]);
        long delta = frames.isEmpty() ? 0 : timestamp - frames.get(frames.size() - 1).getTimestamp();
        frames.add(new Frame(timestamp, delta, lines));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public String getFrame(int i) {
    return frames.get(i).getLines();
  }

  public int numFrames() {
    return frames.size();
  }

  public void play() {
    System.out.println("======================================================");
    frames.forEach(frame -> {
      try {
        long delta = frame.getDelta();
        TimeUnit.MILLISECONDS.sleep(delta);
        System.out.print(frame.getLines());
        System.out.println("======================================================");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  public int getHeight() {
    int maxHeight = -1;

    for (Frame frame : frames) {
      maxHeight = Math.max(maxHeight, frame.getHeight());
    }

    return maxHeight;
  }

  public int getWidth() {
    int maxWidth = -1;

    for (Frame frame : frames) {
      maxWidth = Math.max(maxWidth, frame.getWidth());
    }

    return maxWidth;
  }

  public void createGIF(String path) {
    int height = getHeight() * CHAR_HEIGHT + 10;
    int width = getWidth() * CHAR_WIDTH;
    GifSequenceWriter gif = new GifSequenceWriter(new File(path), 1000, true);

    for (int f = 0; f < frames.size(); ++f) {
      Frame frame = frames.get(f);
      BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics g = img.getGraphics();
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, width, height);
      g.setColor(Color.WHITE);
      g.setFont(Font.decode(Font.MONOSPACED + "-20"));
      String[] lines = frame.getLines().split(System.lineSeparator());

      for (int i = 0; i < lines.length; ++i) {
        g.drawString(lines[i], 0, CHAR_HEIGHT * (i + 1) - 5);
      }

      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(0, height - 10, width, 10);
      g.setColor(Color.RED);
      g.fillRect(0, height - 10, (int) ((double) (f + 1) * width / frames.size()), 10);
      gif.appendFrame(img);
    }

    gif.close();
  }
}
