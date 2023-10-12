package enums;

public enum Qualification {

	IL("Illiterate"), P("Primary"), M("Metric"), I("Intermediate"), G("Graduate"), PG("Post Graduate");

	public final String key;

	private Qualification(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static String findByName(String name) {
		for (Qualification gender : values()) {
			if (gender.name().equals(name)) {
				return gender.getKey();
			}
		}
		return "";
	}
}
