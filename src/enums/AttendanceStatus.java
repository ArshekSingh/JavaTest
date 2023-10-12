package enums;

public enum AttendanceStatus {
    PRESENT("P"), ABSENT("A");

    public final String value;

    private AttendanceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String findByName(String name) {
        for (AttendanceStatus status : values()) {
            if (status.name().equals(name)) {
                return status.getValue();
            }
        }
        return "";
    }
}
