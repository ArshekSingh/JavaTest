package enums;

public enum FileType {

    USER_PROFILE("Profile");

    public final String value;

    FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String findByName(String name) {
        for (FileType fileType : values()) {
            if (fileType.name().equals(name)) {
                return fileType.name();
            }
        }
        return "";
    }
    public static String findValueByName(String key) {
        for (FileType fileType : values()) {
            if (fileType.name().equals(key)) {
                return fileType.getValue();
            }
        }
        return "";
    }
}
