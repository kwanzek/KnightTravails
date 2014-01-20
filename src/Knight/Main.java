package Knight;
import java.util.ArrayList;
import java.util.Scanner;
class Main
{
	public static void main(String[] args)
	{		
		ChessBoard chessboard = new ChessBoard();
		
		boolean done = false;
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the start location using chess notation (e.g. 'b2')");
		String startLocation = reader.next();
		
		System.out.println("Enter the end location using chess notation (e.g. 'e5'");
		String endLocation = reader.next();
		
		ArrayList<String> userInputs = new ArrayList<String>();
		
		while(!done)
		{
			System.out.println("Enter a forbidden square using chess notation or enter 'done' to finish");
			String input = reader.next();
			if(input.equals("done"))
				done = true;
			else
				userInputs.add(input);
		}
				
	    String[] forbiddenSquares = new String[userInputs.size()];
	    forbiddenSquares = userInputs.toArray(forbiddenSquares);
		
		reader.close();
		
		ArrayList<String> shortestPath = chessboard.knightsPath(startLocation, endLocation, forbiddenSquares);
		System.out.println(shortestPath);
	}
}