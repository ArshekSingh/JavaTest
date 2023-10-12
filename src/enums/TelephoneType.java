package enums;

public enum TelephoneType {
	RESIDENCE("P01"), COMPANY("P02"), MOBILE("P03"), PERMANENT("P04"), FOREIGN("P05"), OTHER("P07"), UNTAGGED("P08");

	public final String value;

	private TelephoneType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String findByName(String name) {
		for (TelephoneType gender : values()) {
			if (gender.name().equals(name)) {
				return gender.getValue();
			}
		}
		return null;
	}
}
