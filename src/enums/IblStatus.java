package enums;

public enum IblStatus {
    P("Pending"), V("Validated"), E("Error"), S("Success");

    public final String value;

    private IblStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String findByName(String name) {
        for (IblStatus gender : values()) {
            if (gender.name().equals(name)) {
                return gender.getValue();
            }
        }
        return null;
    }
}
