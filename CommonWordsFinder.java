/*
Author: Daniel Tarkoff
File name: CommonWordsFinder.java
Date: 11.20.2021
Project: Word Trends
*/

import java.util.Comparator;
import java.io.*;

public class CommonWordsFinder {

	public static int parseFile(String filename, PQHeap<KeyValuePair<String, Integer>> heap) throws FileNotFoundException, IOException {
			FileReader reader = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(reader);

			String line = buffer.readLine();

			String[] words = line.split(" ");


			int totalCount = Integer.parseInt(words[3]);
			// totalWordCount = totalCount;


			line = buffer.readLine();

			while (line != null) {

				String[] wordsList = line.split(" ");
				// String[] wordsList = line.split("[^a-zA-Z0-9']");
				String key = wordsList[0].trim().toLowerCase();
				int value = Integer.parseInt(wordsList[1].trim().toLowerCase());

				if (key.length() != 0) {
					KeyValuePair<String, Integer> kvp = new KeyValuePair<>(key, value);
					heap.add(kvp);
					totalCount++;
				}
				line = buffer.readLine();
			}

		reader.close();
		return totalCount;
	}

	
	public static void main(String[] args) throws FileNotFoundException, IOException{

		// int totalWordCount = parseFile("counts_ct.txt", heap);

		int numWords = 10; // number of words we want the frequency of

		String[] fileList = {
			"redditCount_2008.txt",
			"redditCount_2009.txt",
			"redditCount_2010.txt",
			"redditCount_2011.txt",
			"redditCount_2012.txt",
			"redditCount_2013.txt",
			"redditCount_2014.txt",
			"redditCount_2015.txt"
			};

		for (String filename: fileList) {
			// have to do this here or it's the same heap every time
			PQHeap<KeyValuePair<String, Integer>> heap =
			new PQHeap<>((kvp1, kvp2) -> kvp1.getValue() - kvp2.getValue()); // constructing new comparator using lambda function

			int totalWordCount = parseFile(filename, heap);

			System.out.println("Words from " + filename + " file");

			for (int i = 0; i < numWords; i++) {

				int wordCount = heap.remove().getValue(); // gets each value from KVP

				double wordFrequency = ((double) wordCount / totalWordCount); // not working

				System.out.println(heap.remove() + " Word frequency: " + wordFrequency);
			}
			System.out.println("\n\n");
		}

	}

}

// Extension idea: most are the most frequent and RELEVANT words for each file
// out of the top 1000 most frequent words, what words are on one file and not the others?
// take out top 1000 and put it into another file

/*

TODO:
- get counts file for each year
- edit commonwordsfinder not just to print out top words, but the top N words(put in 100 or something)
- print that and their frequency (take value / totalWordCount)
- run that on each year's count file - make it like "mostfreqent" file
- then it's excel stuff


*/




