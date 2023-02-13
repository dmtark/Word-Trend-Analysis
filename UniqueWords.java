/*
Author: Daniel Tarkoff
File name: UniqueWords.java
*/

import java.util.ArrayList;
import java.io.*;
import java.lang.Math;

public class UniqueWords {

	public static ArrayList<String> commonWords() throws FileNotFoundException, IOException{

		MapSet<String, Integer> mostFrequentWords = new HashMap<>(null);

		for (int i = 2008; i < 2015; i++) {

			PQHeap<KeyValuePair<String, Integer>> heap =
			new PQHeap<>((kvp1, kvp2) -> kvp1.getValue() - kvp2.getValue()); // creates a hashmap with our 1000 most frequent words

			String filename = "redditCount" + "_" + i + ".txt";

			CommonWordsFinder.parseFile(filename, heap);

			for (int j = 0; j < 1000; j++) {
				mostFrequentWords.put(heap.remove().getKey(), 0); // not using the value... 
			}

		}
		return mostFrequentWords.keySet();
	}

	public static WordCounter loadTotalWordCounter() {
		WordCounter totalCounter = new WordCounter(); // holds word frequency over all files

		// load every year's words into the totalCounter
		totalCounter.readWordCountFile("redditCount_2008.txt", false);
		totalCounter.readWordCountFile("redditCount_2009.txt", false);
		totalCounter.readWordCountFile("redditCount_2010.txt", false);
		totalCounter.readWordCountFile("redditCount_2011.txt", false);
		totalCounter.readWordCountFile("redditCount_2012.txt", false);
		totalCounter.readWordCountFile("redditCount_2013.txt", false);
		totalCounter.readWordCountFile("redditCount_2014.txt", false);
		totalCounter.readWordCountFile("redditCount_2015.txt", false);
		return totalCounter;
	}

	public static void writeToFile(String year, float newFrequency) {
		/*
		Method to take in a year argument, write to a new text file
		*/
		WordCounter totalCounter = loadTotalWordCounter(); // load in total word counter, all years included

		try {
			FileWriter fileWriter = new FileWriter("reddit_data" + year + ".txt"); // creates text file with correct name
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			// create list of common words over all years
			ArrayList<String> commonWords = commonWords();

			// create counter
			WordCounter counter = new WordCounter();

			// load files into the WordCounters
			String filename = new String("redditcount_" + year + ".txt");
			counter.readWordCountFile(filename); // read in appropriate file

			for (String word: commonWords) { // loop through all words, create frequencies for all
			
				double overallFrequency = totalCounter.getFrequency(word); // to track total frequency over all years
				double wordFrequency = counter.getFrequency(word);
				if (wordFrequency / overallFrequency > newFrequency) {
					bufferedWriter.write(word + "," + wordFrequency + "," + overallFrequency);
					bufferedWriter.newLine();
				}
			}

			bufferedWriter.close();
			System.out.println("File written successfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static void main(String arg1, String arg2) throws FileNotFoundException, IOException{

		ArrayList<String> commonWords = commonWords();
	
		if (arg1 != null && arg2 != null) {

			System.out.println(
					"\nUSAGE: java UniqueWords <year> <increased_frequency_percentage>" + "\n" +
					"Example: java UniqueWords 2008 150 --> shows words from 2008 with words 150% more common in 2008 than the average frequency over all years analyzed\n"
			);
			String yearArg = arg1;		
			float temp = Float.parseFloat(arg2);
			float newFrequency = temp / 100;

			writeToFile(yearArg, newFrequency);

		}

		else { // no arguments given

			System.out.println("NO ARGS");


		} // end else

	}


}