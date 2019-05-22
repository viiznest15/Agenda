package domain;

public class Config {
	private String inputLanguage, outputLanguage;
	private int year, month;

	public Config(String inputLanguage, String outputLanguage, int year, int month) {
		this.inputLanguage = inputLanguage;
		this.outputLanguage = outputLanguage;
		this.year = year;
		this.month = month;
	}

	public String getInputLanguage() {
		return inputLanguage;
	}

	public String getOutputLanguage() {
		return outputLanguage;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public void setInputLanguage(String inputLanguage) {
		this.inputLanguage = inputLanguage;
	}

	public void setOutputLanguage(String outputLanguage) {
		this.outputLanguage = outputLanguage;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Config [inputLanguage=" + inputLanguage + ", outputLanguage=" + outputLanguage + ", year=" + year
				+ ", month=" + month + "]";
	}
}
