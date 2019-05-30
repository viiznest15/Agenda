package services.subservices;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import domain.Config;

public class ServiceConfigCreation {

	public Config getConf(String fileName) {

		String lin = getInfoConf(fileName);
		final byte YEAR = 0, MONTH = 1, INPUTLANGUAGE = 2, OUTPUTLANGUAGE = 3;

		String[] parts = lin.split(" ");

		int year = Integer.parseInt(parts[YEAR]);
		int month = Integer.parseInt(parts[MONTH]);

		String inputLanguage = parts[INPUTLANGUAGE];
		String outputLanguage = parts[OUTPUTLANGUAGE];

		return new Config(inputLanguage, outputLanguage, year, month);
	}

	private String getInfoConf(String fileName) {
		File file = new File(fileName);
		String lin = "";
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				lin += sc.nextLine() + " ";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return lin;
	}

}
