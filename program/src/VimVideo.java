import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        frames.add(new Frame(Long.parseLong(frameData[1]), lines));
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
}
