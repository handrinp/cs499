import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class VimVideo {
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
}
