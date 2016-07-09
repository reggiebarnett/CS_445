//Reggie Barnett
//Assignment 4
import java.util.*;
import java.io.*;

public class Assign4{
	
	private static int total = 0;
	
	public static void main(String args[]) throws Exception
	{	
		int[] startLoc = new int[2];
		Scanner fileMaze = new Scanner(new File(args[0])); //reads in the file you enter
		while(fileMaze.hasNext())
		{
			int[][] maze = loadMaze(fileMaze, startLoc);
			int xloc = startLoc[0];
			int yloc = startLoc[1];
			System.out.println("Searching for solutions starting at ("+xloc+","+yloc+")");
			int step = 0;
			total = 0;
			String[] path = new String[50];
			solveMaze(maze,xloc,yloc,step,path);
			System.out.println("Solutions found: "+total);
			System.out.println("-----------------------------");
		}	
		fileMaze.close();
	}
	
	private static int[][] loadMaze(Scanner fileMaze, int[] startLoc) throws Exception
	{
		int rows = fileMaze.nextInt(); //first int in the file (rows in maze)
		int cols = fileMaze.nextInt(); //second int in the file (columns in maze)
		startLoc[0] = fileMaze.nextInt(); //gives starting location for the row
		startLoc[1] = fileMaze.nextInt(); //gives starting location for the column
		int[][] maze = new int[rows][cols];
		System.out.println("The board is:");
      for(int r = 0; r < rows ; r++) //creates the maze
		{
      	for(int c = 0; c < cols; c++)
			{
         	maze[r][c] = fileMaze.nextInt();
				System.out.print(maze[r][c]+" ");
			}System.out.println();
		}System.out.println();
		return maze;
	}
	
	private static void solveMaze(int[][] maze,int xloc, int yloc, int step, String[] path)
	{
		path[step] = " ("+xloc+","+yloc+")";
		if(maze[xloc][yloc] == 2)//Solution
		{
			total = total+1;
			printMaze(maze,path,step);
			System.out.println();
		}
		if(maze[xloc][yloc] == 0)//Looking for solution
		{	
			maze[xloc][yloc] = 3;
			if(xloc>0 && (maze[xloc-1][yloc]==0 || maze[xloc-1][yloc]==2))//check up
			{
				solveMaze(maze,xloc-1,yloc,step+1,path);
			}
			if(yloc < (maze[0].length-1) && (maze[xloc][yloc+1] ==0 || maze[xloc][yloc+1]==2))//check right
			{
				solveMaze(maze,xloc,yloc+1,step+1,path);
			}
			if(xloc < (maze.length-1) && (maze[xloc+1][yloc]==0 || maze[xloc+1][yloc]==2))//check down
			{
				solveMaze(maze,xloc+1,yloc,step+1,path);
			}
			if(yloc > 0 && (maze[xloc][yloc-1]==0 || maze[xloc][yloc-1]==2))//check left
			{
				solveMaze(maze,xloc,yloc-1,step+1,path);
			}
			maze[xloc][yloc] = 0;
		}
	}
	
	private static void printMaze(int[][] maze,String[] path,int step)
	{
		System.out.println("Solution Found!");
		for(int r = 0; r < maze.length ; r++) //creates the maze
		{
      	for(int c = 0; c < maze[0].length; c++)
			{
				if(maze[r][c] == 3)
				{
					System.out.print("x ");
				}
				else
				{
					System.out.print(maze[r][c]+" ");
				}
			}System.out.println();
		}
		System.out.print("Path:");
		for(int i = 0; i<=step;i++)
		{
			System.out.print(path[i]);
		}System.out.println();
	}
	
}