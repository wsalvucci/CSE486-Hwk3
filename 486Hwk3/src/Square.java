import java.util.ArrayList;

public class Square {
	int value;
	int row;
	int column;
	ArrayList<Integer> possibleValues = new ArrayList<Integer>();
	ArrayList<Integer> boxNeighbors = new ArrayList<Integer>();
	
	public Square(int value) {
		if (value == -1) {
			for (int i=1; i <= 9; i++) {
				possibleValues.add(i);
			}
		} else {
			possibleValues.add(value);
		}
		this.value = value;
	}
	
	public int removePossibleValue(int possibleValue) {
		for (int i=0; i < possibleValues.size(); i++) {
			if (possibleValues.get(i) == possibleValue) {
				possibleValues.remove(i);
				if (possibleValues.size() == 1) {
					this.value = possibleValues.indexOf(0);
				}
				if (possibleValues.size() == 0) {
					System.err.println("Square w/ no possible values");
				}
				return possibleValue;
			}
		}
		return -1;
	}
}
