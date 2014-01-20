package Knight;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

//! ChessBoard.java
//! Class for holding a chess board
//! And finding the shortest path using Breath First Search algorithm
//! As well as additional helper algorithms
public class ChessBoard {

	//! Double array of tiles for the board
	private Tile[][] board;
	
	//! Height and width of a standard chess board
	public final int WIDTH = 8;
	public final int HEIGHT = 8;

	//Offsets for the X and Y values to bring them in line with chess notation
	//Used for converting coordinates
	public static final int ASCII_OFFSET = Tile.ASCII_OFFSET;
	public static final int Y_OFFSET = Tile.Y_OFFSET;

	//Set of tiles we have explored
	private Set<Tile> explored;
	//Queue of points we want to explore
	private LinkedList<Tile> frontier; //Linked List
	
	//Array to get possible moves for a knight
    int[][] moveOffsets = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
        };
	
    //Constructor
    //Set up variables used in the breadth first search
	public ChessBoard()
	{
		board = new Tile[WIDTH][HEIGHT];
		explored = new HashSet<Tile>();
		frontier = new LinkedList<Tile>();
		
		populateBoard();	
	}
	
	//Set up the Tile objects in the board
	private void populateBoard()
	{
		for(int j = 0; j < HEIGHT; ++j)
		{
			for(int i = 0; i < WIDTH; ++i)
			{
				Tile tempTile = new Tile(i, j);
				board[i][j] = tempTile;
			}
		}
	}
	
	//! Method for calculating shortest path
	//! Based on a Breadth First search using a linked list for the frontier
	//! The frontier is the list of nodes we want to expand
	//! The explored set is the list of nodes we  have already found a shorter path to
	//! As there is no edge cost between each node, finding a node again can't be shorter than before
	//! Each time we explore a node we add it to the explored set to prevent re-exploring
	public ArrayList<String> knightsPath(String startLocation, String endLocation, String[] forbiddenSquares)
	{
		ArrayList<String> path = new ArrayList<String>();
		
		Tile startNode = getTileAtAlgebraLocation(startLocation);
		Tile goalNode = getTileAtAlgebraLocation(endLocation);
		ArrayList<Tile> forbidden = new ArrayList<Tile>();
		
		for(int i = 0; i<forbiddenSquares.length; ++i)
		{
			forbidden.add(getTileAtAlgebraLocation(forbiddenSquares[i]));
		}
		
		if(startNode == null || goalNode == null)
		{
			return path;
		}
		
		frontier.addFirst(startNode);
		explored.add(startNode);
		
		Tile node;
		while(!frontier.isEmpty())
		{
			node = frontier.removeFirst();
			
			if(node == goalNode)
			{
				//we have found the goal so we need to backtrace to get the shortest path
				Tile tempNode = goalNode;
				path.add(goalNode.getChessNotation());
				
				//Each node has a "parent" which will get us back to the start
				while(tempNode!=startNode)
				{
					path.add(0, tempNode.getParent().getChessNotation());
					tempNode = tempNode.getParent();
				}
				//If we found the shortest path we are done so no need to keep looping
				break;
			}
			
			//Get all the possible neighbors, and ignore the forbidden ones
			ArrayList<Tile> neighbors = getNeighborsOfTile(node);
			for(Tile t : neighbors)
			{				
				if(!explored.contains(t) && !forbidden.contains(t))
				{
					//Here we set the node's parent to the current node
					//As this is the shortest path we know of to get to this node
					t.setParent(node);
					explored.add(t);
					frontier.add(t);
				}
			}
		}
		return path;
	}
	
	//!Get the tile that corresponds to the algebra string location
	//!This is necessary because the input is algebraic ("a4")
	//!But we need Tile objects to test for equality easily
	private Tile getTileAtAlgebraLocation(String location)
	{
		Tile returnTile = null;
		
		if(location.length() != 2)
		{
			//Not valid chess location
			return null;
		}
		else
		{
			char letter = location.charAt(0);
			char number = location.charAt(1);
			
			//Have to offset Ascii value to return it to a numeric value
			int asciiLetter = letter - ASCII_OFFSET;
			
			//Have to offset for going from 1 to 8 instead of 0 to 7
			int yNumber = Character.getNumericValue(number)-Y_OFFSET;
			
			if(yNumber < 0 || yNumber >= HEIGHT || asciiLetter < 0 || asciiLetter >= WIDTH)
			{
				//Incorrect location
				return null;
			}
			
			returnTile = board[asciiLetter][yNumber];		
		}
		return returnTile;
	}
	
	//!Get all the possible neighbors of a tile
	//!This uses the "moveOffsets" array which is all the possible directions
	//!a knight could move from a square
	//!Not all possibilities are inbounds though
	private ArrayList<Tile> getNeighborsOfTile(Tile tile)
	{
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		int x = tile.getX();
		int y = tile.getY();
		
		for(int i = 0; i < moveOffsets.length; ++i)
		{
			int[] move = moveOffsets[i];
			int newX = x+move[0];
			int newY = y+move[1];
			if(inBounds(newX, newY))
				neighbors.add(board[newX][newY]);
		}
		
		return neighbors;
	}
	
	//Just used to test if a location is inbounds or not
	private boolean inBounds(int x, int y)
	{
		if(x>=0 && x<board[0].length && y >=0 && y<board.length)
			return true;
		else 
			return false;
	}	
}
