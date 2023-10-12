package enums;

public enum AddressType {
	
	RESIDENCE("D01"), COMPANY("D02"), RES_CUM_OFF("D03"), PERMANENT("D04"), CURRENT("D05"), FOREIGN("D06"),
	MILITARY("D07"), OTHER("D08");

	public final String value;

	private AddressType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String findByName(String name) {
		for (AddressType gender : values()) {
			if (gender.name().equals(name)) {
				return gender.getValue();
			}
		}
		return RESIDENCE.getValue();
	}
	
}
