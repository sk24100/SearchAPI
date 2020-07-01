package shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

	// // ystem.getProperty("user.dir")+"\\src\\main\\java\\shared\\APIData.csv
	// public static void main(String[] args) throws FileNotFoundException {
	////
	// }

	public static List<APIdata> getCSV() throws IOException {

		File inventoryFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\shared\\APIData.csv");
//		System.out.println(inventoryFile.exists());
		Scanner inventoryScanner = new Scanner(inventoryFile);
		inventoryScanner.useDelimiter("[,\n]");

		List<APIdata> ls = new ArrayList<APIdata>();

		while (inventoryScanner.hasNext()) // returns a boolean value
		{
			// Creating an object out of each row
			APIdata apiData = new APIdata();
			apiData.setUserId(inventoryScanner.next());
			apiData.setUrl(inventoryScanner.next());
			apiData.setRequestType(inventoryScanner.next());
			apiData.setParameter(inventoryScanner.next());
			apiData.setBody(inventoryScanner.next());
			apiData.setExpected1(inventoryScanner.next());
			apiData.setExpected2(inventoryScanner.next());

			System.out.println("Row Data loaded :: details are : \n" + apiData.toString());
			// Adding the row to to a List
			ls.add(apiData);
		}
		// Should return the number of rows
		System.out.println("SIZE IS  + " + ls.size());

		inventoryScanner.close(); // closes the scanner

		return ls;
			
	}

}

