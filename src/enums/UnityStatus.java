package enums;

public enum UnityStatus {

    P("Pending"), V("Validated"), E("Error"), S("Success"), SRC("Sourcing"), DISB("Disbursement");
    public final String value;

    private UnityStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String findByName(String name) {
        for (UnityStatus value : values()) {
            if (value.name().equals(name)) {
                return value.getValue();
            }
        }
        return null;
    }
}
