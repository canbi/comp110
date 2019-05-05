import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Can Bi
 * @since February 18, 2019 Finds and displays the most frequent number in the
 *        input text file
 */
public class MostFrequentNumbers {
	/**
	 * Loads inputs into the string array from the text file. Converts string array
	 * into integer array.
	 *
	 * @param filename File name which the method will open.
	 * @return Returns int array which stores numbers which come from the file
	 */
	public static int[] loadNumbers(String filename) {

		// opens the text file
		File myFile = new File(filename);
		Scanner input = null;
		try {
			input = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			System.out.println(filename + ": Input file can not be found!\nExiting program...");
			System.exit(1);
		}

		// line number which the file has
		int lineNumber = lineCounter(filename);

		String[] inputArray = new String[lineNumber];

		int count = 0;
		while (input.hasNext()) {
			// input file lines are string
			String line = input.nextLine();

			inputArray[count] = line;
			count++;
		}

		int[] numbers = toIntArray(inputArray);

		return numbers;
	}

	/**
	 * Trims and parses string values to int values.
	 *
	 * @param array String array that has numbers as string
	 * @return Returns int array that stores numbers
	 */
	public static int[] toIntArray(String[] array) {

		int[] numbers = new int[array.length];

		for (int i = 0; i < array.length; i++) {

			String temp = array[i];
			temp = temp.trim(); // trim

			int intValue = Integer.parseInt(temp); // parse
			numbers[i] = intValue;
		}

		return numbers;
	}

	/**
	 * Creates frequency array. Frequency array calculates and stores the frequency
	 * of numbers Assuming that numbers are in the range of [0..999]. Because of
	 * that length of frequency array is 1000.
	 *
	 * @param numbers Int array which has integer values in the range of [0-999]
	 * @return Returns int array which stores numbers frequency
	 */
	public static int[] computeFrequency(int[] numbers) {

		int[] frequency = new int[1000];

		for (int i = 0; i < numbers.length; i++) {
			frequency[numbers[i]]++;
		}

		return frequency;
	}

	/**
	 * This method finds how many line in the array.
	 *
	 * @param filename File name which the method will open.
	 * @return Returns number of line which the file has, as a integer value.
	 */
	public static int lineCounter(String filename) {

		// opens the text file
		File file = new File(filename);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(filename + ": Input file can not be found!\nExiting program...");
			System.exit(1);
		}

		int lineCount = 0;
		while (input.hasNext()) {
			input.nextLine();
			lineCount++;
		}
		return lineCount;
	}

	/**
	 * Finds most frequent number and stores count of frequency and its index into
	 * an array.
	 *
	 * @param array Int array which has integer values
	 * @return Returns int array which has count of frequency of the number and its
	 *         index(the number) from frequency array
	 */
	public static int[] findMax(int[] array) {

		int[] storeArray = new int[2]; // stores number and frequency
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {

				max = array[i];
				array[i] = 0;

				storeArray[0] = i; // number
				storeArray[1] = max; // frequency

			}
		}
		return storeArray;
	}

	/**
	 * Displays most frequent number.
	 *
	 * @param array Int array which has integer values
	 */
	public static void displayFrequent(int[] array) {

		int[][] frequentNums = new int[32][2];
		// Hypothetically, there can not more than 32 numbers that
		// they have same frequency. >> 32*32=1024

		for (int i = 0; i < frequentNums.length; i++) {
			int[] storeArray = findMax(array);

			frequentNums[i][0] = storeArray[0]; // number
			frequentNums[i][1] = storeArray[1]; // frequency

		}

		int stopCount = 0;
		for (int i = 0; i < frequentNums.length - 1; i++) {

			if (frequentNums[i][1] != frequentNums[i + 1][1]) {
				stopCount = i;

				break;

			}
		}

		System.out.println("Most Frequent Numbers:");
		for (int i = 0; i <= stopCount; i++) {

			System.out.printf("Number: %2d, Frequency: %2d\n", frequentNums[i][0], frequentNums[i][1]);

		}
	}

	public static void main(String[] args) {

		int[] numbers = loadNumbers("data1.txt");

		int[] frequency = computeFrequency(numbers);

		displayFrequent(frequency);

	}
}
