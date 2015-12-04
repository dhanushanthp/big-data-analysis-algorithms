package org.mahout.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class MarketBasketDataTOMahoutFormat {
	public static void main(String args[]) throws Exception {
		BufferedReader csvReader = new BufferedReader(new FileReader("data/marketbasket.csv"));

		String line = csvReader.readLine();
		String[] tokens = line.split(",");

		// Generate the map between products and the integer values
		FileWriter mappingWriter = new FileWriter("data/mapping.csv");
		int i = 0;
		for (String token : tokens) {
			mappingWriter.write(token.trim() + "," + i + "\n");
			i++;
		}
		mappingWriter.close();

		// Generate output file based on generate map
		FileWriter datWriter = new FileWriter("data/output.dat");
		int transactionCount = 0;
		while (true) {
			line = csvReader.readLine();
			if (line == null) {
				break;
			}

			tokens = line.split(",");
			i = 0;
			boolean isFirstElement = true;
			for (String token : tokens) {
				if (token.trim().equals("true")) {
					if (isFirstElement) {
						isFirstElement = false;
					} else {
						datWriter.append(",");
					}
					datWriter.append(Integer.toString(i));
				}
				i++;
			}
			datWriter.append("\n");
			transactionCount++;
		}
		datWriter.close();
		csvReader.close();
		System.out.println("Wrote " + transactionCount + " transactions.");
	}
}