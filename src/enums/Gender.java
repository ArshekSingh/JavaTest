package enums;

public enum Gender {

    M("G01", "Male"), F("G02", "Female"), O("G03", "Other");

    public final String key;
    public final String value;

    Gender(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String findByName(String name) {
        for (Gender gender : values()) {
            if (gender.name().equals(name)) {
                return gender.getKey();
            }
        }
        return "";
    }

    public String getKey() {
        return key;
    }

    public static String findByKey(String key) {
        for (Gender gender : values()) {
            if (gender.name().equals(key)) {
                return gender.getValue();
            }
        }
        return "";
    }

    public String getValue() {
        return value;
    }
}
