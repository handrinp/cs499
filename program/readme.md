Key Movie Recording Program
===
This program is kind of like a voluntary keylogger which can be used to record, replay, and view keystrokes over time,
kind of like a movie.
It will hopefully facilitate the learning of Vim, since Vim is very keystroke heavy.

One part of the program is an addition to the .vimrc, which facilitates recording.
To use this, append the vimrc file in this directory to the end of your .vimrc file.
Then, while vim is open, type `:let record=1` to begin recording, and `:let record=0` to finish recording.

There is also a second part of the program which can kind of "play back" the videos, which is written in Java.
To play back the video, first compile the java code in the src directory with `javac *.java`.
Then, you can run the program with `java Main <filename>`, where "filename" refers to the recorded file to playback.
Note: leave off the ".rec" when running the java program.
This will play a "video" of sorts, of the recording.
