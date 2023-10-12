package enums;

public enum ApplicationStatus {

	A("Approved"), B("Credit Bureau"), C("CGT"), X("Rejected"), G("GRT"), R("Registered"), C2("CGT-2");

	public final String key;

	private ApplicationStatus(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static String findByName(String name) {
		for (ApplicationStatus appStatus : values()) {
			if (appStatus.name().equals(name)) {
				return appStatus.getKey();
			}
		}
		return "";
	}

}
