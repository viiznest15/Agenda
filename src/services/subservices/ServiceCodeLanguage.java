package services.subservices;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServiceCodeLanguage {
	public Map<String, String[]> getLanguageCodes(String language) {

		Map<String, String[]> codeValueLanguage = new HashMap<>();

		File file = new File("resources/internacional." + language);
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				String[] parts = split(lin.replace(" ", ""));
				codeValueLanguage.put(parts[0], parts[1].split(","));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return codeValueLanguage;
	}

	private String[] split(String lin) {
		String[] parts = lin.split(";");
		return parts;
	}
}
