package view;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import services.subservices.ServiceCodeLanguage;

public class HtmlGenerator {
	String valueClosed;

	public HtmlGenerator(String string) {
		this.valueClosed = string;
	}

	public void escribirFichero(List<String[][]> semanas) {

		File top = new File("resources/top.txt");

		try (Scanner sc = new Scanner(top);
				DataOutputStream dos = new DataOutputStream(new FileOutputStream("output/sala1.html"))) {
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				dos.writeBytes(lin);
			}
			initHtml(dos);
			for (String[][] semana : semanas)
				insertTable(dos, semana);
			closeHtml(dos);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeHtml(DataOutputStream dos) throws IOException {
		dos.writeBytes("</body>\r\n" + "</html>");
	}

	private void insertTable(DataOutputStream dos, String[][] peticiones) throws IOException {
		dos.writeBytes("<table align=center width=90% border=1 cellpadding=2 cellspacing=0>\n");
		dos.writeBytes("<tr style=\"text-align: center; background-color:#D6E7FA;\">\n");
		String color = "";
		for (int row = 0; row < peticiones.length; row++) {
			if (row != 0 && row != 1)
				dos.writeBytes("<tr style=\"text-align: center;\">\n");
			else if (row != 0)
				dos.writeBytes("<tr style=\"text-align: center; background-color:#E8F1FE;\">\n");
			for (int col = 0; col < peticiones[0].length; col++) {
				if (row == 0) {
					dos.writeBytes("<td style=\"border: 1px solid black;\">");
				} else {
					if (row == 1 || (col == 0 && row != 0))
						dos.writeBytes("<td style=\"border: 1px solid black; background-color:#E8F1FE;\">");
					else {
						if (closed(peticiones[row][col], valueClosed))
							color = "#b2aaaa;";
						else if (reunion(peticiones[row][col]))
							color = "#fbfccf;";
						else
							color = "#DFFAC4;";
						setColor(dos, color);
					}
				}
				dos.writeBytes(peticiones[row][col]);
				dos.writeBytes("</td>\n");
			}
			dos.writeBytes("</tr>\n");
		}
		dos.writeBytes("</table><br><br>\n");
	}

	private void setColor(DataOutputStream dos, String color) throws IOException {
		dos.writeBytes("<td style=\"border: 1px solid black;background-color:" + color + "\">");
	}

	private void initHtml(DataOutputStream dos) throws IOException {
		String titulo1 = "Sala1";
		String titulo2 = "January 2008";

		dos.writeBytes("<h1 align=center>\n" + titulo1 + "</h1>\n" + "<h2 align=center>" + titulo2 + "</h2>\n");
	}

	private boolean reunion(String field) {
		return field.toLowerCase().substring(0, 1).equals("r");
	}

	private boolean closed(String field, String valueClosed) {
		return field.equalsIgnoreCase(valueClosed);
	}

}
