package enums;

public enum Religion {

    HI("Hindu"), MU("Muslim"),SI("SIKH"),CR("Christian"),OT("Other");

    public final String key;

    private Religion(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static String findByName(String name) {
        for (Religion appStatus : values()) {
            if (appStatus.name().equals(name)) {
                return appStatus.getKey();
            }
        }
        return "";
    }
}
