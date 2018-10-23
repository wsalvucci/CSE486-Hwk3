import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class mainEngine {
	static Square[][] board = new Square[9][9];

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("board1.txt"));
			int row = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				for (int i=0; i < line.length(); i++) {
					if (line.charAt(i) == '-') {
						board[i][row] = new Square(-1);
					} else {
						board[i][row] = new Square(Character.getNumericValue(line.charAt(i)));
					}
					System.out.print(board[i][row].value);
				}
				System.out.println("");
				row++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		forwardCheck();
		printBoard();
	}
	
	public static void printBoard() {
		for (int i=0; i < board.length; i++) {
			for (int j=0; j < board[i].length; j++) {
				if (board[j][i].value != -1) {
					System.out.print(board[j][i].value);
				} else {
					System.out.print("(");
					for (int value : board[j][i].possibleValues) {
						System.out.print(", " + value);
					}
					System.out.print(")");
				}
			}
			System.out.println("");
		}
	}
	
	public static void forwardCheck() {
		for (int i=0; i < board.length; i++) {
			for (int j=0; j < board[i].length; j++) {
				//-- Check if it has been filled out --//
				if (board[i][j].value != -1) {
					//-- Eliminate all possible values on the same row (shares column number) --//
					for (int c = 0; c < board.length; c++) {
						if (board[c][j].value == -1) {
							board[c][j].removePossibleValue(board[i][j].value);
						}
					}
					// -- Eliminate all possible values on the same column (shares row number) --//
					for (int r = 0; r < board[i].length; r++) {
						if (board[i][r].value == -1) {
							board[i][r].removePossibleValue(board[i][j].value);
						}
					}
					
					//-- Determine which "block" it is a part of --//
					double blockCol = Math.floor(i/3);
					double blockRow = Math.floor(j/3);
					
					System.out.println("(" + blockCol + ", " + blockRow + ")");
					
					//-- Eliminate all possible values in same block (shares same 3x3) --//
					for (int bc = (int) (blockCol*3); bc < (blockCol*3) + 3; bc++) {
						for (int br = (int) (blockRow*3); br < (blockRow*3) + 3; br++) {
							if (board[bc][br].value == -1) {
								board[bc][br].removePossibleValue(board[i][j].value);
								System.out.println("Removing " + board[i][j].value + " from square " + bc + "," + br);
							}
						}
					}
				}
			}
		}
		System.out.println("");
	}

}
