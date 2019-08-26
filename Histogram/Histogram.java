import java.util.Scanner;

public class Histogram {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int[] asterisksCount = new int[27]; // Stores how many asterisks to put out there, default 0
		
		while (input.hasNext()) {
			String userInput = input.next();
			userInput = userInput.toLowerCase(); // convert all characters to lowercase
			
			for (int i = 0; i < userInput.length(); i++) { 
				char compare = userInput.charAt(i);
				alphabetCompare(asterisksCount, compare);
			}
		}
		printResult(asterisksCount);
	}

	// Prints the histogram
	public static void printResult(int[] asterisksCount) {
		for (int j = 97; j < 123; j++) {
			char alphabet = (char)j;
			System.out.print(alphabet + ":");
			for (int l = 0; l < asterisksCount[j - 97]; l++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	// This method iterates through the user inputs and detects how many of each character there are in the string
	public static void alphabetCompare(int[] asterisksCount, char compare) {
		if (compare == 'a') 
			asterisksCount[0]++;
		if (compare == 'b') 
			asterisksCount[1]++;
		if (compare == 'c') 
			asterisksCount[2]++;
		if (compare == 'd') 
			asterisksCount[3]++;
		if (compare == 'e')
			asterisksCount[4]++;
		if (compare == 'f')
			asterisksCount[5]++;
		if (compare == 'g')
			asterisksCount[6]++;
		if (compare == 'h')
			asterisksCount[7]++;
		if (compare == 'i')
			asterisksCount[8]++;
		if (compare == 'j')
			asterisksCount[9]++;
		if (compare == 'k')
			asterisksCount[10]++;
		if (compare == 'l')
			asterisksCount[11]++;
		if (compare == 'm')
			asterisksCount[12]++;
		if (compare == 'n')
			asterisksCount[13]++;
		if (compare == 'o')
			asterisksCount[14]++;
		if (compare == 'p')
			asterisksCount[15]++;
		if (compare == 'q')
			asterisksCount[16]++;
		if (compare == 'r')
			asterisksCount[17]++;
		if (compare == 's')
			asterisksCount[18]++;
		if (compare == 't')
			asterisksCount[19]++;
		if (compare == 'u')
			asterisksCount[20]++;
		if (compare == 'v')
			asterisksCount[21]++;
		if (compare == 'w')
			asterisksCount[22]++;
		if (compare == 'x')
			asterisksCount[23]++;
		if (compare == 'y')
			asterisksCount[24]++;
		if (compare == 'z')
			asterisksCount[25]++;
		else
			asterisksCount[26]++;
	}
}