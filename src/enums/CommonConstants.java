package enums;

public enum CommonConstants {

    CREATE("CREATE"),
    UPDATE("UPDATE");

    public final String value;

    private CommonConstants(String value) {
        this.value = value;
    }
}
