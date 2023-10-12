package enums;

public enum LoanStatus {

	A("Active"), D("Death"), I("Sent to Insurer"), X("Closed"), R("Reversed"), U("Payment Pending"), W("Write-off"),
	L("Delinquent");

	public final String value;

	private LoanStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String findByName(String name) {
		for (LoanStatus gender : values()) {
			if (gender.name().equals(name)) {
				return gender.getValue();
			}
		}
		return null;
	}
}
