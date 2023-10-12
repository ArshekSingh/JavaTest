package enums;

public enum RepaymentFrequency {

    TB("Two Bi-Weekly"), DL("Daily"),WK("Weekly"),BI("Bi-Weekly"),MO("Monthly");

    public final String key;

    private RepaymentFrequency(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static String findByName(String name) {
        for (RepaymentFrequency appStatus : values()) {
            if (appStatus.name().equals(name)) {
                return appStatus.getKey();
            }
        }
        return "";
    }
}
