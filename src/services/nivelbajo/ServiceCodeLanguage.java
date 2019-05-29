package services.nivelbajo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServiceCodeLanguage {
	public Map<String, String> getLanguageCodes(String language) {

		Map<String, String> codeValueLanguage = new HashMap<>();

		File fichero = new File("resources/internacional." + language);
		try (Scanner sc = new Scanner(fichero)) {
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				String[] parts = split(lin);
				codeValueLanguage.put(parts[0], parts[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return codeValueLanguage;
	}

	public String[] split(String lin) {
		String[] parts = lin.split(";");
		return parts;
	}
}
