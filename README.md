# Java Maze Solver

This is a program written in Java that solves mazes using recursion. Once the program starts a file explorer window opens and allows the user to load a maze file (format is specified below). After a maze file is loaded into memory, a GUI (graphical user interface) showing the maze and a control panel is displayed. The user can then click on any field of the maze and be presented with one of (possibly) many solutions. A button that shows the shortest possible solution to the maze is also provided. 

Below follows a short demo of the program in action. 
![Maze Solver Demo](mazeDemo2.gif)



## File format 
The maze file must be of a very specific format.
1. The first line must specify the dimentions of the file (height x width).
2. The following lines specify the shape of the maze. To create a black square (a wall) use "#", and to create a white square (open square) use ".". 
3. The file extention should be ".in".

## About 
This program was created as a mandatory assignment in the course IN1010 (Object Oriented Programming in Java) at The University of Oslo. It was written in the spring of 2021, in the second semester of my Computer Science Degree.

Feel free to download the code and test it your self. You could also try to create your own maze and see if the program can solve it (it should)!  

## Start program 
To start this program you must first compile the Java code:

```javac Main.java```

When that is done, start the program:

```java Main```

