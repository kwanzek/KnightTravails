package Knight;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Tests {

	@Test
	public void test1() {
		
		ChessBoard chessboard = new ChessBoard();
		
		String startLocation = "b2";
		String endLocation = "e5";
		String[] forbiddenSquares = {"c4", "d3"};
		
		ArrayList<String> shortestPath = chessboard.knightsPath(startLocation, endLocation, forbiddenSquares);
		
		ArrayList<String> truePath = new ArrayList<String>();
		truePath.add("b2");
		truePath.add("a4");
		truePath.add("b6");
		truePath.add("d7");
		truePath.add("e5");
		
		for(int i = 0; i < shortestPath.size(); ++i)
			assertEquals(shortestPath.get(i), truePath.get(i));
	}
	
	@Test
	public void test2() {
		
		ChessBoard chessboard = new ChessBoard();
		
		String startLocation = "a1";
		String endLocation = "h8";
		String[] forbiddenSquares = {};
		
		ArrayList<String> shortestPath = chessboard.knightsPath(startLocation, endLocation, forbiddenSquares);
		assertTrue(shortestPath.size()!=0);
	}

}
