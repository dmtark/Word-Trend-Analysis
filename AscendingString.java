/*
Author: Daniel Tarkoff
File name: AscendingString.java
Date: 11.20.2021
Project: Word Trends
*/

import java.util.Comparator;

public class AscendingString implements Comparator<String> {

	public int compare(String o1, String o2) {
		if (o1.equals(o2)) {
			return 0;
		}
		else if (o1.compareTo(o2) > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}

}