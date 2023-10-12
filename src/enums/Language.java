package enums;

public enum Language {

	EN("English"), HN("Hindi"), PB("Punjabi");

	public final String key;

	private Language(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static String findByKey(String name) {
		for (Language gender : values()) {
			if (gender.name().equals(name)) {
				return gender.getKey();
			}
		}
		return "";
	}
}
