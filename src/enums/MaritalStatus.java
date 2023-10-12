package enums;

public enum MaritalStatus {

	M("Married"), S("Single"), W("Widowed"), D("Divorced");

	public final String key;

	private MaritalStatus(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static String findByKey(String name) {
		for (MaritalStatus gender : values()) {
			if (gender.name().equals(name)) {
				return gender.getKey();
			}
		}
		return "";
	}
}
