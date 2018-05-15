Key Movie Recording Program
===
This program is kind of like a voluntary keylogger which can be used to record, replay, and view keystrokes over time,
kind of like a movie.
It will hopefully facilitate the learning of Vim, since Vim is very keystroke heavy.

One part of the program is an addition to the .vimrc, which facilitates recording.
To use this, append the vimrc file in this directory to the end of your .vimrc file.
Then, while vim is open, type `:let record=1` to begin recording, and `:let record=0` to finish recording.

There is also a second part of the program written in Java.
This second program can either play back the recordings, or save them in GIF form.

To use this tool, first compile the java code in the src directory with `javac *.java`.
Then, you can run the program with `java Main <vrec-file> [destination-gif-file]`.
If you leave off the second argument, then the program will just play back the recording.
If you include the second argument, a GIF will be generated as well.
