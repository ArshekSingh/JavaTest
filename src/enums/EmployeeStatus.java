package enums;

public enum EmployeeStatus {

    A("Active"), X("Inactive");

    public final String key;

    private EmployeeStatus(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static String findByName(String name) {
        for (EmployeeStatus appStatus : values()) {
            if (appStatus.name().equals(name)) {
                return appStatus.getKey();
            }
        }
        return "";
    }
}
