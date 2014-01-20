package Knight;
//! Tile.java
//! Class for holding information about a square on a chess board
//! This class is an object to add in retrieving the x,y position
//! As well as the algebraic notation of the path
public class Tile {

	//! The tile's position in algebraic chess notation
	//! The X axis goes from A to H and the Y axis goes from 1 to 8
	//! For example the Tile in location 2,1 will be c2
	private String chess_notation;
	
	//! Numeric location of the tile on the chessboard
	private int x;
	private int y;
	
	//Parent for finding shortest path
	private Tile parent;
	
	//Chess notation begins at 1, instead of 0 like our arrays
	public static final int Y_OFFSET = 1;
	
	//!For offseting the x location into chess notation with ascii
	public static final int ASCII_OFFSET = 97;
	
	public Tile(int x, int y)
	{
		this.x = x;
		this.y = y;
		chess_notation = setChessNotation();
		parent = null;
	}
	
	private String setChessNotation()
	{
		String chessNotation =(Character.toString((char) (x+ASCII_OFFSET)) + (y+Y_OFFSET));
		return chessNotation;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String getChessNotation()
	{
		return chess_notation;
	}
	
	public String toString()
	{
		return "This tile is " + chess_notation + " at location ("+ x +", "+ y +")";
	}
	
	public void setParent(Tile tile)
	{
		parent = tile;
	}
	
	public Tile getParent()
	{
		return parent;
	}
	
}
