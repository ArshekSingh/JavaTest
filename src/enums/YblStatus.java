package enums;

public enum YblStatus {

	P("Pending"), V("Validated"), E("Error"), S("Success");

	public final String value;

	private YblStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String findByName(String name) {
		for (YblStatus gender : values()) {
			if (gender.name().equals(name)) {
				return gender.getValue();
			}
		}
		return null;
	}
}
