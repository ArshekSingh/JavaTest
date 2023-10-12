package enums;

public enum IdType {

	PASSPORT("PAS", "ID01"), VOTER_ID("VT", "ID02"), UID("AD", "ID03"), OTHERS("GT", "ID04"), RATION_CARD("RC", "ID05"),
	DRIVING_LICENSE_NO("DL", "ID06"), PAN("PA", "ID07");

	public final String key;
	public final String value;

	private IdType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static String findByKey(String key) {
		for (IdType gender : values()) {
			if (gender.getKey().equals(key)) {
				return gender.getValue();
			}
		}
		return OTHERS.getValue();
	}

	public static String findNameByKey(String key) {
		for (IdType idType : values()) {
			if (idType.getValue().equals(key)) {
				return idType.getValue();
			}
		}
		return OTHERS.getValue();
	}

}
