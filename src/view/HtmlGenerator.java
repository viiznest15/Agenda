package view;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import domain.Table;
import services.subservices.ServiceCodeLanguage;

public class HtmlGenerator {
	private String[] valueClosed;
	private int year;

	public HtmlGenerator(String[] strings, int year) {
		this.valueClosed = strings;
		this.year = year;
	}

	public void writeFile(List<Table> tables) {

		File top = new File("resources/top.txt");

		try (Scanner sc = new Scanner(top);
				DataOutputStream dos = new DataOutputStream(new FileOutputStream("output/sala1.html"))) {
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				dos.writeBytes(lin);
			}
			initHtml(dos);
			for (Table week : tables)
				insertTable(dos, week.getTable());
			closeHtml(dos);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void insertTable(DataOutputStream dos, String[][] requests) throws IOException {
		beginTable(dos);
		String color = "";
		for (int row = 0; row < requests.length; row++) {
			if (row != 0 && row != 1)
				setNormalRow(dos);
			else if (row != 0)
				setDayRow(dos);
			for (int col = 0; col < requests[0].length; col++) {
				if (row == 0)
					setFirstRow(dos);
				else {
					if (row == 1 || (col == 0 && row != 0))
						setSecondRowFirstColumn(dos);
					else {
						if (isClosed(requests[row][col], valueClosed))
							color = "#b2aaaa;";
						else if (isReunion(requests[row][col]))
							color = "#fbfccf;";
						else
							color = "#DFFAC4;";
						setColor(dos, color);
					}
				}
				setData(dos, requests, row, col);
			}
			closeRow(dos);
		}
		closeTable(dos);
	}

	private void closeRow(DataOutputStream dos) throws IOException {
		dos.writeBytes("</tr>\n");
	}

	private void setData(DataOutputStream dos, String[][] peticiones, int row, int col) throws IOException {
		if (peticiones[row][col] != null)
			dos.writeBytes(peticiones[row][col]);
		else
			dos.writeBytes("</td>\n");
	}

	private void closeTable(DataOutputStream dos) throws IOException {
		dos.writeBytes("</table><br><br>\n");
	}

	private void setFirstRow(DataOutputStream dos) throws IOException {
		dos.writeBytes("<th style=\"border: 1px solid black;\">");
	}

	private void setNormalRow(DataOutputStream dos) throws IOException {
		dos.writeBytes("<tr style=\"text-align: center;\">\n");
	}

	private void setDayRow(DataOutputStream dos) throws IOException {
		dos.writeBytes("<tr style=\"text-align: center; background-color:#E8F1FE;\">\n");
	}

	private void setSecondRowFirstColumn(DataOutputStream dos) throws IOException {
		dos.writeBytes("<td style=\"border: 1px solid black; background-color:#E8F1FE;\">");
	}

	private void beginTable(DataOutputStream dos) throws IOException {
		dos.writeBytes("<table align=center width=90% border=1 cellpadding=2 cellspacing=0>\n");
		dos.writeBytes("<tr style=\"text-align: center; background-color:#D6E7FA;\">\n");
	}

	private void setColor(DataOutputStream dos, String color) throws IOException {
		dos.writeBytes("<td style=\"border: 1px solid black; background-color:" + color + "\">");
	}

	private void initHtml(DataOutputStream dos) throws IOException {
		String titulo1 = "Sala1";
		String titulo2 = "January " + year;
		dos.writeBytes("<h1 align=center>\n" + titulo1 + "</h1>\n" + "<h2 align=center>" + titulo2 + "</h2>\n");
	}

	private void closeHtml(DataOutputStream dos) throws IOException {
		dos.writeBytes("</body>\r\n" + "</html>");
	}

	private boolean isReunion(String field) {
		if (field == null)
			return false;
		else
			return field.toLowerCase().contains("reunio");
	}

	private boolean isClosed(String field, String[] valueClosed2) {
		if (field == null)
			return false;
		else
			return field.equalsIgnoreCase(valueClosed2[0]);
	}
}
